package com.gs.lshly.rpc.api.bbb.h5.commodity;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsLabelQTO;

import java.util.List;

/**
*
* @author Starry
* @since 2020-11-11
*/
public interface IBbbH5GoodsLabelRpc {

    /**
     * 获取所有打上推荐标签的商品id
     * @param qto
     * @return
     */
    List<String> listGoodsId(BbbH5GoodsLabelQTO.QTO qto);

}