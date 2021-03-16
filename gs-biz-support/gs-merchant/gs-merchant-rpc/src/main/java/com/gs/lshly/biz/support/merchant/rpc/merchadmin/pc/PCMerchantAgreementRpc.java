package com.gs.lshly.biz.support.merchant.rpc.merchadmin.pc;

import com.gs.lshly.biz.support.merchant.service.merchadmin.pc.IPCMerchantAgreementService;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchantAgreementVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchantAgreementRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-06
*/
@DubboService
public class PCMerchantAgreementRpc implements IPCMerchantAgreementRpc {

    @Autowired
    private IPCMerchantAgreementService merchantAgreementService;


    @Override
    public PCMerchantAgreementVO.DetailVO detailMerchantAgreement(BaseDTO dto) {
        return merchantAgreementService.detailMerchantAgreement(dto);
    }
}