package com.gs.lshly.biz.support.commodity.service.common;

import com.gs.lshly.common.struct.common.CommonStockDTO;

import java.util.List;

/**
 * 商品售卖数量接口（临时接口）
 * @author jdt
 * @date 2021/3/19 10:54
 */
public interface ICommonGoodsSaleQuantityService {

    /**
     * 内部調用，增加銷售量
     * @param goodsId
     * @param addSaleQuatity
     * @return
     */
    boolean innerAddSaleQuatityForTrade (String goodsId,int addSaleQuatity);

}