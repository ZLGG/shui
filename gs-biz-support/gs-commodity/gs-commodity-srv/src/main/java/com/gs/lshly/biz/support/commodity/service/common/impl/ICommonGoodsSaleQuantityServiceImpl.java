package com.gs.lshly.biz.support.commodity.service.common.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gs.lshly.biz.support.commodity.entity.GoodsInfo;
import com.gs.lshly.biz.support.commodity.repository.IGoodsInfoRepository;
import com.gs.lshly.biz.support.commodity.service.common.ICommonGoodsSaleQuantityService;
import com.gs.lshly.common.exception.BusinessException;

/**
 * @author jdt
 * @date 2021/3/19 13:28
 */
public class ICommonGoodsSaleQuantityServiceImpl implements ICommonGoodsSaleQuantityService {

    @Autowired
    private IGoodsInfoRepository repository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean innerAddSaleQuatityForTrade(String goodsId, int addSaleQuatity) {
        if (StringUtils.isEmpty(goodsId)) {
            throw new BusinessException("商品id不能为空");
        }
        GoodsInfo goodInfo = repository.getById(goodsId);
        if (goodInfo == null) {
            throw new BusinessException("商品不能为空");
        }
        goodInfo.setSaleQuantity(goodInfo.getSaleType() + addSaleQuatity);
        boolean b = repository.updateById(goodInfo);
        return b;
    }
}