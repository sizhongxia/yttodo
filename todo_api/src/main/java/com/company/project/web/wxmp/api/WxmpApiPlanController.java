package com.company.project.web.wxmp.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.annotation.WxTokenCheck;
import com.company.project.common.utils.IdUtils;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.model.TdPlan;
import com.company.project.service.TdPlanService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cn.hutool.core.date.DateUtil;
import tk.mybatis.mapper.entity.Condition;

@RestController
@RequestMapping("/wxmp/api/plan")
public class WxmpApiPlanController {

	Logger logger = LoggerFactory.getLogger(WxmpApiPlanController.class);

	@Resource
	TdPlanService tdPlanService;

	/**
	 * 获取用户基本信息
	 * 
	 * @param request
	 * @return
	 */
	@WxTokenCheck
	@RequestMapping("/data")
	public Result<?> data(HttpServletRequest request, @RequestBody Map<String, String> param) {
		String userId = (String) request.getAttribute("userId");
		String status = param.get("status");
		if (StringUtils.isBlank(status)) {
			status = "R";
		}
		Condition condition = new Condition(TdPlan.class);
		condition.createCriteria().andEqualTo("userId", userId).andEqualTo("planStatus", status);
		String pageStr = param.get("page");
		int page = 1;
		if (NumberUtils.isDigits(pageStr)) {
			page = Integer.parseInt(pageStr);
		}
		if ("R".equals(status)) {
			condition.orderBy("importanceLevel").desc().orderBy("urgentLevel").desc().orderBy("createAt").desc();
		} else {
			condition.orderBy("updateAt").desc();
		}
		PageHelper.startPage(page, 10, true);
		List<TdPlan> plans = tdPlanService.findByCondition(condition);

		boolean over = true;
		List<Map<String, Object>> list = new ArrayList<>();
		if (plans != null && plans.size() > 0) {
			Map<String, Object> item = null;
			for (TdPlan plan : plans) {
				item = new HashMap<>();
				item.put("planId", plan.getPlanId());
				item.put("planStatus", plan.getPlanStatus());
				item.put("planContent", plan.getPlanContent());
				item.put("importanceLevel", plan.getImportanceLevel());
				item.put("urgentLevel", plan.getUrgentLevel());
				if ("R".equals(status)) {
					item.put("dueAt", DateUtil.format(plan.getDueAt(), "yyyy-MM-dd"));
				} else {
					item.put("dueAt", DateUtil.format(plan.getUpdateAt(), "yyyy-MM-dd HH:mm"));
				}
				list.add(item);
			}
			Page<TdPlan> pageInfo = (Page<TdPlan>) plans;
			over = (page >= pageInfo.getPages());
			pageInfo.close();
		}

		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("over", over);
		return ResultGenerator.genSuccessResult(map);
	}

	@WxTokenCheck
	@RequestMapping("/createPlan")
	public Result<?> createPlan(HttpServletRequest request, @RequestBody Map<String, String> param) {
		String userId = (String) request.getAttribute("userId");
		String planContent = param.get("planContent");
		if (StringUtils.isBlank(planContent)) {
			return ResultGenerator.genFailResult("请输入计划内容");
		}
		if (planContent.length() > 220) {
			return ResultGenerator.genFailResult("计划内容长度超出限制");
		}
		String importanceLevel = param.get("importanceLevel");
		if (!NumberUtils.isDigits(importanceLevel)) {
			return ResultGenerator.genFailResult("请选择重要级别");
		}
		String urgentLevel = param.get("urgentLevel");
		if (!NumberUtils.isDigits(urgentLevel)) {
			return ResultGenerator.genFailResult("请选择紧急级别");
		}
		String dueAtStr = param.get("dueAt");
		if (StringUtils.isBlank(dueAtStr)) {
			return ResultGenerator.genFailResult("请选择计划预计完成时间");
		}
		String formId = param.get("formId");
		if (StringUtils.isBlank(formId)) {
			formId = "";
		}
		Date dueAt = DateUtil.parse(dueAtStr);
		TdPlan plan = new TdPlan();
		plan.setUserId(userId);
		plan.setPlanId(IdUtils.initObjectId());
		plan.setPlanContent(planContent);
		plan.setImportanceLevel(Integer.parseInt(importanceLevel));
		plan.setUrgentLevel(Integer.parseInt(urgentLevel));
		plan.setPlanStatus("R");
		plan.setDueAt(dueAt);
		plan.setFormId(formId);
		plan.setCreateAt(DateUtil.date());
		plan.setUpdateAt(DateUtil.date());
		tdPlanService.save(plan);
		return ResultGenerator.genSuccessResult(true);
	}

	@WxTokenCheck
	@RequestMapping("/finishPlan")
	public Result<?> finishPlan(HttpServletRequest request, @RequestBody Map<String, String> param) {
		String userId = (String) request.getAttribute("userId");
		String planId = param.get("planId");
		if (StringUtils.isBlank(planId)) {
			return ResultGenerator.genFailResult("无效的计划");
		}
		TdPlan plan = tdPlanService.findBy("planId", planId);
		if (plan == null || !plan.getUserId().equals(userId)) {
			return ResultGenerator.genFailResult("无效的计划");
		}
		plan.setPlanStatus("Y");
		plan.setUpdateAt(DateUtil.date());
		tdPlanService.update(plan);
		return ResultGenerator.genSuccessResult(true);
	}

	@WxTokenCheck
	@RequestMapping("/canclePlan")
	public Result<?> canclePlan(HttpServletRequest request, @RequestBody Map<String, String> param) {
		String userId = (String) request.getAttribute("userId");
		String planId = param.get("planId");
		if (StringUtils.isBlank(planId)) {
			return ResultGenerator.genFailResult("无效的计划");
		}
		TdPlan plan = tdPlanService.findBy("planId", planId);
		if (plan == null || !plan.getUserId().equals(userId)) {
			return ResultGenerator.genFailResult("无效的计划");
		}
		plan.setPlanStatus("N");
		plan.setUpdateAt(DateUtil.date());
		tdPlanService.update(plan);
		return ResultGenerator.genSuccessResult(true);
	}

	@WxTokenCheck
	@RequestMapping("/deletePlan")
	public Result<?> deletePlan(HttpServletRequest request, @RequestBody Map<String, String> param) {
		String userId = (String) request.getAttribute("userId");
		String planId = param.get("planId");
		if (StringUtils.isBlank(planId)) {
			return ResultGenerator.genFailResult("无效的计划");
		}
		TdPlan plan = tdPlanService.findBy("planId", planId);
		if (plan == null || !plan.getUserId().equals(userId)) {
			return ResultGenerator.genFailResult("无效的计划");
		}
		plan.setPlanStatus("D");
		plan.setUpdateAt(DateUtil.date());
		tdPlanService.update(plan);
		return ResultGenerator.genSuccessResult(true);
	}
}
