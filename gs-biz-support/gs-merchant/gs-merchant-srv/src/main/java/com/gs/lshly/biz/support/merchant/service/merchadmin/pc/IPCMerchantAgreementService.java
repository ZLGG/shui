package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchantAgreementVO;

public interface IPCMerchantAgreementService {

    /**
     * 获取注册协议
     * @param dto
     * @return
     */
    PCMerchantAgreementVO.DetailVO detailMerchantAgreement(BaseDTO dto);

}