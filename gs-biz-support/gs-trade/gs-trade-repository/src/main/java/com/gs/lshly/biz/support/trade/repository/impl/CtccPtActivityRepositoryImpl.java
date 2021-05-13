package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.CtccPtActivity;
import com.gs.lshly.biz.support.trade.mapper.CtccPtActivityMapper;
import com.gs.lshly.biz.support.trade.repository.ICtccPtActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yangxi
 * @create 2021/5/8 10:27
 */
@Service
public class CtccPtActivityRepositoryImpl extends ServiceImpl<CtccPtActivityMapper,CtccPtActivity> implements ICtccPtActivityRepository {

    @Autowired
    private CtccPtActivityMapper ctccPtActivityMapper;

}
