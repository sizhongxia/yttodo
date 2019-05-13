package com.company.project.service.impl;

import com.company.project.dao.yt.TdTokenMapper;
import com.company.project.model.TdToken;
import com.company.project.service.TdTokenService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Yeetong.CN on 2019/05/13.
 */
@Service
@Transactional
public class TdTokenServiceImpl extends AbstractService<TdToken> implements TdTokenService {
    @Resource
    private TdTokenMapper tdTokenMapper;

}
