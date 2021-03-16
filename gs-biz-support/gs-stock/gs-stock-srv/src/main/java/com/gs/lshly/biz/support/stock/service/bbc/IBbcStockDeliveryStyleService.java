package com.gs.lshly.biz.support.stock.service.bbc;

import com.gs.lshly.common.struct.bbc.stock.dto.BbcStockDeliveryDTO;
import com.gs.lshly.common.struct.bbc.stock.vo.BbcStockDeliveryVO;

public interface IBbcStockDeliveryStyleService {

    BbcStockDeliveryVO.DeliveryAmountVO calculate(BbcStockDeliveryDTO.DeliveryAmountDTO dto);
}