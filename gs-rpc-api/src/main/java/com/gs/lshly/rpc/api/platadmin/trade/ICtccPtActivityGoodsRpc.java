package com.gs.lshly.rpc.api.platadmin.trade;

import java.util.List;

/**
 * @Author hanly
 * @create 2021/6/2 9:35
 */

public interface ICtccPtActivityGoodsRpc {

    List<String> getList();
    
    /**
     * 跟据商品ID批量下架
     * @param goodsIds
     */
    void underStateByGoodsId(List<String> goodsIds);
}
