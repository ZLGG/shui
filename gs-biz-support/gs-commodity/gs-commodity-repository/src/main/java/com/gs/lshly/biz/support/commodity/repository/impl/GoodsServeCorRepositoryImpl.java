package com.gs.lshly.biz.support.commodity.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.commodity.entity.GoodsServeCor;
import com.gs.lshly.biz.support.commodity.mapper.GoodsServeCorMapper;
import com.gs.lshly.biz.support.commodity.repository.IGoodsServeCorRepository;
import org.springframework.stereotype.Service;

@Service
public class GoodsServeCorRepositoryImpl extends ServiceImpl<GoodsServeCorMapper, GoodsServeCor> implements IGoodsServeCorRepository {
}
