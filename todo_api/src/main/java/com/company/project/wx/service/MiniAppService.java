package com.company.project.wx.service;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.company.project.common.utils.HttpClientUtils;
import com.company.project.configurer.RedisUtils;

@Component
public class MiniAppService {
	static final Logger logger = LoggerFactory.getLogger(MiniAppService.class);

	@Resource
	RedisUtils redisUtils;

	/**
	 * 获取小程序AccessToken
	 * 
	 * @return
	 */
	public String getAccessToken() {
		String redisKey = "miniapp:accesstoken:" + WxConst.appId;
		Object token = redisUtils.get(redisKey);
		if (token != null) {
			return token.toString();
		}
		String apiUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WxConst.appId + "&secret=" + WxConst.appSecret;
		String res = null;
		try {
			res = HttpClientUtils.get(apiUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (StringUtils.isBlank(res)) {
			return null;
		}
		JSONObject json = JSON.parseObject(res);
		String accessToken = json.getString("access_token");
		if (StringUtils.isBlank(accessToken)) {
			return null;
		}
		redisUtils.set(redisKey, accessToken, 7140);
		return accessToken;
	}

}
