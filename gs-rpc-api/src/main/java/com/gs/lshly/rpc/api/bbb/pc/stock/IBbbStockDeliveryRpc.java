package com.gs.lshly.rpc.api.bbb.pc.stock;

import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockDeliveryVO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;

/**
* 店铺物流远程服务
* @author lxus
* @since 2020-11-02
*/
public interface IBbbStockDeliveryRpc {

    BbbStockDeliveryVO.DeliveryAmountVO calculate(BbbStockDeliveryDTO.DeliveryAmountDTO dto);
}