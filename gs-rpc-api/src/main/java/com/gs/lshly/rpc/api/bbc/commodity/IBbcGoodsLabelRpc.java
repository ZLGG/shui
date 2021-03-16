package com.gs.lshly.rpc.api.bbc.commodity;

import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsLabelQTO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-11-11
*/
public interface IBbcGoodsLabelRpc {

    /**
     * 获取所有打上推荐标签的商品id
     * @param qto
     * @return
     */
    List<String> listGoodsId(BbcGoodsLabelQTO.QTO qto);

}