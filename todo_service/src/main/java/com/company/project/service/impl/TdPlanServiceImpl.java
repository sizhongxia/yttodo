package com.company.project.service.impl;

import com.company.project.dao.yt.TdPlanMapper;
import com.company.project.model.TdPlan;
import com.company.project.service.TdPlanService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Yeetong.CN on 2019/05/14.
 */
@Service
@Transactional
public class TdPlanServiceImpl extends AbstractService<TdPlan> implements TdPlanService {
    @Resource
    private TdPlanMapper tdPlanMapper;

}
