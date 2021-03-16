package com.gs.lshly.rpc.api.bbc.stock;

import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;

/**
* 店铺物流远程服务
* @author lxus
* @since 2020-11-02
*/
public interface IBbcStockDeliveryRpc {

    BbcStockDeliveryVO.DeliveryAmountVO calculate(BbcStockDeliveryDTO.DeliveryAmountDTO dto);
}