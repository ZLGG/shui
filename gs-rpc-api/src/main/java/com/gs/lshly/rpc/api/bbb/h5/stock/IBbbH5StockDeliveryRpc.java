package com.gs.lshly.rpc.api.bbb.h5.stock;

import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockDeliveryVO;

/**
* 店铺物流远程服务
* @author lxus
* @since 2020-11-02
*/
public interface IBbbH5StockDeliveryRpc {

    BbbH5StockDeliveryVO.DeliveryAmountVO calculate(BbbH5StockDeliveryDTO.DeliveryAmountDTO dto);
}