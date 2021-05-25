package com.gs.lshly.biz.support.trade.repository.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gs.lshly.biz.support.trade.entity.CtccActivityGoods;
import com.gs.lshly.biz.support.trade.mapper.CtccActivityGoodsMapper;
import com.gs.lshly.biz.support.trade.repository.ICtccActivityGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/24 22:02
 */
@Service
public class CtccActivityGoodsRepositoryImpl extends ServiceImpl<CtccActivityGoodsMapper, CtccActivityGoods> implements ICtccActivityGoodsRepository {
    @Autowired
    private CtccActivityGoodsMapper ctccActivityGoodsMapper;

    @Override
    public List<CtccActivityGoods> getActivityIdByGoodsId(List<String> goodsIds) {
        List<CtccActivityGoods> list = ctccActivityGoodsMapper.selectList(new QueryWrapper<CtccActivityGoods>().in("goods_id", goodsIds));
        return list;
    }
}
