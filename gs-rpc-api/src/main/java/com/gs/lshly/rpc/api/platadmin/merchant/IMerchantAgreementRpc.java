package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAgreementDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;

/**
*
* @author xxfc
* @since 2020-10-06
*/
public interface IMerchantAgreementRpc {

    void editMerchantAgreement(MerchantAgreementDTO.ETO eto);

    MerchantAgreementVO.DetailVO detailMerchantAgreement(MerchantAgreementDTO.IdDTO dto);

}