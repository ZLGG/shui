package com.gs.lshly.biz.support.stock.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.stock.dto.BbbH5StockDeliveryDTO;
import com.gs.lshly.common.struct.bbb.h5.stock.vo.BbbH5StockDeliveryVO;

public interface IBbbH5StockDeliveryStyleService {

    BbbH5StockDeliveryVO.DeliveryAmountVO calculate(BbbH5StockDeliveryDTO.DeliveryAmountDTO dto);
}