package com.gs.lshly.rpc.api.bb.trade;

import com.gs.lshly.common.struct.bb.trade.dto.RechargeOrderDTO;
import com.gs.lshly.common.struct.bb.trade.qto.RechargeOrderQTO;
import com.gs.lshly.common.struct.bb.trade.vo.BbRechargeVO;

/**
 * 
 * 话费充值 
 * 
 * @author yingjun
 * @date 2021年3月16日 下午1:30:26
 */
public interface IBbRechargeOrderRpc {
	
	/**
	 * 获取当前用户的话费余额信息
	 * @return
	 */
    BbRechargeVO.PhoneBillVO getPhoneBill(RechargeOrderQTO.QTO qto);
    
    /**
     * 充值
     * @param eto
     */
    void create(RechargeOrderDTO.ETO eto);
}
