package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchantDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchantVO;

public interface IPCMerchantService {

    /**
     * 获取注册协议
     * @param dto
     * @return
     */
    PCMerchantVO.DetailVO getMerchant(PCMerchantDTO.DTO dto);
    
    /**
     * 
     * @param eto
     */
    void modifyMerchant(PCMerchantDTO.ETO eto);

}