package com.gs.lshly.biz.support.trade.travelsky;

import com.gs.lshly.common.struct.bbc.trade.dto.BbcTravelskyDTO;

public interface ITravelskyOrderService {

	void createOrder(BbcTravelskyDTO.ETO dto);
	
	
	String getOrder();
}
