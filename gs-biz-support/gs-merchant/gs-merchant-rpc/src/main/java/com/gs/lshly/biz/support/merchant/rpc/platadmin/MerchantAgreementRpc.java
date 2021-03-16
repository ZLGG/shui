package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantAgreementService;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAgreementDTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAgreementVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantAgreementRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author xxfc
* @since 2020-10-06
*/
@DubboService
public class MerchantAgreementRpc implements IMerchantAgreementRpc{

    @Autowired
    private IMerchantAgreementService merchantAgreementService;


    @Override
    public void editMerchantAgreement(MerchantAgreementDTO.ETO eto){
        merchantAgreementService.editMerchantAgreement(eto);
    }

    @Override
    public MerchantAgreementVO.DetailVO detailMerchantAgreement(MerchantAgreementDTO.IdDTO dto){
        return merchantAgreementService.detailMerchantAgreement(dto);
    }

}