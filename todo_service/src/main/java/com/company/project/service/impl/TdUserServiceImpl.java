package com.company.project.service.impl;

import com.company.project.dao.yt.TdUserMapper;
import com.company.project.model.TdUser;
import com.company.project.service.TdUserService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Yeetong.CN on 2019/05/13.
 */
@Service
@Transactional
public class TdUserServiceImpl extends AbstractService<TdUser> implements TdUserService {
    @Resource
    private TdUserMapper tdUserMapper;

}
