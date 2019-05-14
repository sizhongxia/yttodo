package com.company.project.service.impl;

import com.company.project.dao.yt.TdUserLikeMapper;
import com.company.project.model.TdUserLike;
import com.company.project.service.TdUserLikeService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Yeetong.CN on 2019/05/14.
 */
@Service
@Transactional
public class TdUserLikeServiceImpl extends AbstractService<TdUserLike> implements TdUserLikeService {
    @Resource
    private TdUserLikeMapper tdUserLikeMapper;

}
