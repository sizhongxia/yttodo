package com.company.project.service.impl;

import com.company.project.dao.yt.TdUserWxMapper;
import com.company.project.model.TdUserWx;
import com.company.project.service.TdUserWxService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Yeetong.CN on 2019/05/13.
 */
@Service
@Transactional
public class TdUserWxServiceImpl extends AbstractService<TdUserWx> implements TdUserWxService {
    @Resource
    private TdUserWxMapper tdUserWxMapper;

}
