package com.gs.lshly.biz.support.commodity.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsLabelQTO;
import java.util.List;

public interface IBbbH5GoodsLabelService {

    /**
     * 获取所有打上推荐标签的商品id
     * @param qto
     * @return
     */
    List<String> listGoodsId(BbbH5GoodsLabelQTO.QTO qto);

}