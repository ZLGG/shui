package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.CtccActivityGoods;
import com.gs.lshly.biz.support.trade.mapper.CtccActivityGoodsMapper;
import com.gs.lshly.biz.support.trade.repository.ICtccActivityGoodsRepository;
import org.springframework.stereotype.Service;

/**
 * @Author yangxi
 * @create 2021/5/24 22:02
 */
@Service
public class CtccActivityGoodsRepositoryImpl extends ServiceImpl<CtccActivityGoodsMapper, CtccActivityGoods> implements ICtccActivityGoodsRepository {
}
