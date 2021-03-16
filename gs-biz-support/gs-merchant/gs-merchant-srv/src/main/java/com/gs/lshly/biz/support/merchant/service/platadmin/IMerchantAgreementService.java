package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAgreementDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;

public interface IMerchantAgreementService {

    void editMerchantAgreement(MerchantAgreementDTO.ETO eto);

    MerchantAgreementVO.DetailVO detailMerchantAgreement(MerchantAgreementDTO.IdDTO dto);

}