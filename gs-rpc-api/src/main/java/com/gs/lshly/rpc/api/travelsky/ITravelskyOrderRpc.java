package com.gs.lshly.rpc.api.travelsky;

import com.gs.lshly.common.struct.bbc.trade.dto.BbcTravelskyDTO;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月16日 下午1:30:26
 */
public interface ITravelskyOrderRpc {
	
    void createOrder(BbcTravelskyDTO.ETO eto);
}
