package com.company.project.web.wxmp.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.WxTokenCheck;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TdUser;
import com.company.project.model.TdUserLike;
import com.company.project.service.TdUserLikeService;
import com.company.project.service.TdUserService;

import cn.hutool.core.date.DateUtil;

@RestController
@RequestMapping("/wxmp/api/user")
public class WxmpApiUserController {

	Logger logger = LoggerFactory.getLogger(WxmpApiUserController.class);

	@Resource
	TdUserService tdUserService;
	@Resource
	TdUserLikeService tdUserLikeService;

	/**
	 * 获取用户基本信息
	 * 
	 * @param request
	 * @return
	 */
	@WxTokenCheck
	@RequestMapping("/userInfo")
	public Result<?> userInfo(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		TdUser user = tdUserService.findBy("userId", userId);
		if (user == null) {
			return ResultGenerator.genFailResult("请重新登陆");
		}
		Map<String, Object> map = new HashMap<>();
		map.put("userId", user.getUserId());
		map.put("nickName", user.getNickName());
		map.put("headImgUrl", user.getHeadImgUrl());
		return ResultGenerator.genSuccessResult(map);
	}

	@WxTokenCheck
	@RequestMapping("/getUserLike")
	public Result<?> getUserLike(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		TdUserLike userLike = tdUserLikeService.findBy("userId", userId);
		return ResultGenerator.genSuccessResult(userLike != null);
	}

	@WxTokenCheck
	@RequestMapping("/setUserLike")
	public Result<?> setUserLike(HttpServletRequest request) {
		String userId = (String) request.getAttribute("userId");
		TdUserLike userLike = tdUserLikeService.findBy("userId", userId);
		if (userLike == null) {
			userLike = new TdUserLike();
			userLike.setUserId(userId);
			userLike.setClickAt(DateUtil.date());
			tdUserLikeService.save(userLike);
		}
		return ResultGenerator.genSuccessResult(userLike != null);
	}
}
