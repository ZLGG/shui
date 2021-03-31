package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchantService;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchantDTO.DTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchantDTO.ETO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchantVO.DetailVO;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchantRpc;

/**
*
* @author xxfc
* @since 2020-10-06
*/
@DubboService
public class PCMerchantRpc implements IPCMerchantRpc {

    @Autowired
    private IPCMerchantService pcmerchantService;

	@Override
	public DetailVO getMerchant(DTO dto) {
		return pcmerchantService.getMerchant(dto);
	}

	@Override
	public void modifyMerchant(ETO eto) {
		pcmerchantService.modifyMerchant(eto);
		
	}


   
}