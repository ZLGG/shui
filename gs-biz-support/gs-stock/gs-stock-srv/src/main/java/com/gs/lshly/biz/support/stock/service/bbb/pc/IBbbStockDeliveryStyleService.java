package com.gs.lshly.biz.support.stock.service.bbb.pc;

import com.gs.lshly.common.struct.bbb.pc.stock.vo.BbbStockDeliveryVO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockDeliveryDTO;

public interface IBbbStockDeliveryStyleService {

    BbbStockDeliveryVO.DeliveryAmountVO calculate(BbbStockDeliveryDTO.DeliveryAmountDTO dto);
}