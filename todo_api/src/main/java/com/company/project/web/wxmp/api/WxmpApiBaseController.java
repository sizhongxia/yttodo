package com.company.project.web.wxmp.api;

import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.codehaus.xfire.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.company.project.common.utils.HttpClientUtils;
import com.company.project.common.utils.IdUtils;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TdToken;
import com.company.project.model.TdUser;
import com.company.project.model.TdUserWx;
import com.company.project.service.TdTokenService;
import com.company.project.service.TdUserService;
import com.company.project.service.TdUserWxService;
import com.company.project.wx.service.WxConst;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/wxmp/api")
public class WxmpApiBaseController {

	// token有效时长
	public final static int TOKEN_VALID_TIME = 24 * 7;

	Logger logger = LoggerFactory.getLogger(WxmpApiBaseController.class);

	@Resource
	TdUserWxService tdUserWxService;
	@Resource
	TdUserService tdUserService;
	@Resource
	TdTokenService tdTokenService;

	@RequestMapping("/login")
	public Result<?> login(HttpServletRequest request, @RequestBody Map<String, String> param) {
		if (StringUtils.isBlank(param.get("code"))) {
			return ResultGenerator.genFailResult("无效的微信'code'");
		}
		Map<String, String> obj = jscode2session(param.get("code"));
		if (obj == null) {
			return ResultGenerator.genFailResult("请求微信服务失败");
		}
		String openId = obj.get("openId");
		if (StringUtils.isBlank(openId)) {
			return ResultGenerator.genFailResult("请求微信服务失败");
		}
		String sessionKey = obj.get("sessionKey");
		if (sessionKey == null) {
			return ResultGenerator.genFailResult("请求微信服务失败");
		}
		TdUserWx uw = tdUserWxService.findBy("openId", openId);
		String userId = null;
		if (uw == null) {
			String nickName = param.get("nickName");
			if (StringUtils.isBlank(nickName)) {
				return ResultGenerator.genFailResult("请先完成微信授权");
				// nickName = "微信用户" + RandomUtil.randomNumbers(6);
			}
			String avatarUrl = param.get("avatarUrl");
			if (StringUtils.isBlank(avatarUrl)) {
				return ResultGenerator.genFailResult("请先完成微信授权");
			}
			String encryptedData = param.get("encryptedData");
			if (StringUtils.isBlank(encryptedData)) {
				return ResultGenerator.genFailResult("请先完成微信授权");
			}
			String iv = param.get("iv");
			if (StringUtils.isBlank(iv)) {
				return ResultGenerator.genFailResult("请先完成微信授权");
			}
			String rawData = param.get("rawData");
			if (StringUtils.isBlank(rawData)) {
				return ResultGenerator.genFailResult("请先完成微信授权");
			}
			String signature = param.get("signature");
			if (StringUtils.isBlank(signature)) {
				return ResultGenerator.genFailResult("请先完成微信授权");
			}
			String sign = SecureUtil.sha1(rawData + sessionKey);
			if (!signature.equals(sign)) {
				return ResultGenerator.genFailResult("数据被篡改，请重新授权");
			}
			String unionId = obj.get("unionId");
			if (StringUtils.isBlank(unionId)) {
				JSONObject res = getUserInfo(encryptedData, sessionKey, iv);
				unionId = res.getString("unionId");
				if (StringUtils.isBlank(unionId)) {
					unionId = "";
				}
			}
			String gender = param.get("gender");
			if (StringUtils.isBlank(gender)) {
				gender = "";
			}
			String city = param.get("city");
			if (StringUtils.isBlank(city)) {
				city = "";
			}
			String province = param.get("province");
			if (StringUtils.isBlank(province)) {
				province = "";
			}
			String country = param.get("country");
			if (StringUtils.isBlank(country)) {
				country = "";
			}
			TdUser member = createNewMember(nickName, avatarUrl, city, province, country, gender);
			userId = member.getUserId();
			// }
			createMemberWx(userId, openId, unionId, sessionKey);
		} else {
			userId = uw.getUserId();
		}
		Condition condition = new Condition(TdToken.class);
		condition.createCriteria().andEqualTo("userId", userId).andEqualTo("sourceType", "MP").andEqualTo("isForbidden", 0).andGreaterThan("overdueAt", DateUtil.date());
		List<TdToken> userTokens = tdTokenService.findByCondition(condition);
		TdToken userToken = null;
		if (userTokens != null && userTokens.size() > 0) {
			userToken = userTokens.get(0);
			userToken.setLastVisitAt(DateUtil.date());
			userToken.setOverdueAt(DateUtil.offset(DateUtil.date(), DateField.HOUR, TOKEN_VALID_TIME));
			tdTokenService.update(userToken);
		} else {
			userToken = createMpToken(userId);
		}
		return ResultGenerator.genSuccessResult(userToken.getToken());
	}

	private TdToken createMpToken(String userId) {
		TdToken userToken = new TdToken();
		userToken.setUserId(userId);
		userToken.setToken(IdUtil.simpleUUID() + Long.toString(System.currentTimeMillis(), 16));
		userToken.setSourceType("MP");
		userToken.setCreateAt(DateUtil.date());
		userToken.setLastVisitAt(DateUtil.date());
		userToken.setOverdueAt(DateUtil.offset(DateUtil.date(), DateField.HOUR, TOKEN_VALID_TIME));
		userToken.setIsForbidden(0);
		tdTokenService.save(userToken);
		return userToken;
	}

	private void createMemberWx(String userId, String openId, String unionId, String sessionKey) {
		TdUserWx uw;
		uw = new TdUserWx();
		uw.setResId(IdUtils.initObjectId());
		uw.setUserId(userId);
		uw.setAppId(WxConst.appId);
		uw.setOpenId(openId);
		uw.setUnionId(unionId);
		uw.setSessionKey(sessionKey);
		uw.setVersion(1);
		uw.setUpdateAt(DateUtil.date());
		uw.setCreateAt(DateUtil.date());
		tdUserWxService.save(uw);
	}

	private TdUser createNewMember(String nickName, String avatarUrl, String city, String province, String country, String gender) {
		TdUser member = new TdUser();
		member.setUserId(IdUtils.initObjectId());
		member.setNickName(nickName);
		member.setHeadImgUrl(avatarUrl);
		member.setWxCity(city);
		member.setWxCountry(country);
		member.setWxGender(gender);
		member.setWxProvince(province);
		member.setDeleteFlag("N");
		member.setVersion(1);
		member.setCreateAt(DateUtil.date());
		member.setUpdateAt(DateUtil.date());
		tdUserService.save(member);
		return member;
	}

	private Map<String, String> jscode2session(String code) {
		String resJson = null;
		try {
			resJson = HttpClientUtils.get("https://api.weixin.qq.com/sns/jscode2session?appid=" + WxConst.appId + "&secret=" + WxConst.appSecret + "&js_code=" + code + "&grant_type=authorization_code");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		if (resJson == null) {
			logger.error("请求微信服务失败01");
			return null;
		}
		JSONObject jData = JSONObject.parseObject(resJson);
		if (jData == null) {
			logger.error("请求微信服务失败02");
			return null;
		}
		Map<String, String> map = new HashMap<>();
		map.put("sessionKey", jData.getString("session_key"));
		map.put("openId", jData.getString("openid"));
		map.put("unionId", jData.getString("unionid"));
		return map;
	}

	private JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
		// 被加密的数据
		byte[] dataByte = Base64.decode(encryptedData);
		// 加密秘钥
		byte[] keyByte = Base64.decode(sessionKey);
		// 偏移量
		byte[] ivByte = Base64.decode(iv);
		try {
			int base = 16;
			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
