package com.gs.lshly.biz.support.trade.rpc.travelsky;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.trade.travelsky.ITravelskyOrderService;
import com.gs.lshly.rpc.api.travelsky.ITravelskyOrderRpc;

@DubboService
public class TravelskyOrderRpc implements ITravelskyOrderRpc {

    @Autowired
    private ITravelskyOrderService orderService;

	@Override
	public String createOrder() {
		return orderService.createOrder();
		
	}
    
    
}
