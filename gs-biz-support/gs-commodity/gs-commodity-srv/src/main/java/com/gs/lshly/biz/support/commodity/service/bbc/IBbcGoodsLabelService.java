package com.gs.lshly.biz.support.commodity.service.bbc;

import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsLabelQTO;

import java.util.List;

public interface IBbcGoodsLabelService {

    /**
     * 获取所有打上推荐标签的商品id
     * @param qto
     * @return
     */
    List<String> listGoodsId(BbcGoodsLabelQTO.QTO qto);

}