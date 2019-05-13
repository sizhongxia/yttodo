package com.company.project.Interceptor;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.company.project.annotation.WxTokenCheck;
import com.company.project.model.TdToken;
import com.company.project.model.TdUser;
import com.company.project.service.TdTokenService;
import com.company.project.service.TdUserService;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

public class WxTokenCheckInterceptor extends HandlerInterceptorAdapter {

	@Resource
	TdTokenService tdTokenService;
	@Resource
	TdUserService tdUserService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			WxTokenCheck tokenCheck = (WxTokenCheck) ((HandlerMethod) handler).getMethodAnnotation(WxTokenCheck.class);
			if (tokenCheck != null) {
				final String token = request.getHeader("token");
				if (StringUtils.isBlank(token)) {
					response.setStatus(401);
					return false;
				}
				Date now = new Date();
				Condition condition = new Condition(TdToken.class);
				condition.createCriteria().andEqualTo("token", token).andEqualTo("isForbidden", 0).andGreaterThan("overdueAt", now);
				PageHelper.startPage(1, 1);
				List<TdToken> tokens = tdTokenService.findByCondition(condition);
				if (tokens == null || tokens.isEmpty()) {
					response.setStatus(401);
					return false;
				}
				TdToken userToken = tokens.get(0);
				TdUser cu = tdUserService.findBy("userId", userToken.getUserId());
				if (cu == null) {
					response.setStatus(401);
					return false;
				}
				userToken.setLastVisitAt(now);
				userToken.setOverdueAt(DateUtil.offset(now, DateField.HOUR, 24 * 7));
				tdTokenService.update(userToken);
				request.setAttribute("userId", userToken.getUserId());
			}
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
