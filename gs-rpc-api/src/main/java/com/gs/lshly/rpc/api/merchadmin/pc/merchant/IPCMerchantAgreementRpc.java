package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchantAgreementVO;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAgreementDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;

/**
*
* @author xxfc
* @since 2020-10-06
*/
public interface IPCMerchantAgreementRpc {

    /**
     * 获取注册协议
     * @param dto
     * @return
     */
    PCMerchantAgreementVO.DetailVO detailMerchantAgreement(BaseDTO dto);
}