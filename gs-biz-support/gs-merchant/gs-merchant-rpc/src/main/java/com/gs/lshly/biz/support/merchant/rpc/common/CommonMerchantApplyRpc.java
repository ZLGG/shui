package com.gs.lshly.biz.support.merchant.rpc.common;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.struct.common.dto.CommonMerchantApplyDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantApplyQTO;
import com.gs.lshly.common.struct.common.vo.CommonMerchantApplyVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.biz.support.merchant.service.common.ICommonMerchantApplyService;
import com.gs.lshly.rpc.api.common.ICommonMerchantApplyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-14
*/
@DubboService
public class CommonMerchantApplyRpc implements ICommonMerchantApplyRpc {

    @Autowired
    private ICommonMerchantApplyService commonMerchantApplyService;


    @Override
    public String editMerchantApply(CommonMerchantApplyDTO.ETO eto){
       return commonMerchantApplyService.editMerchantApply(eto);
    }

    @Override
    public CommonMerchantApplyVO.DetailVO detailMerchantApply(CommonMerchantApplyDTO.QueryDTO dto){
        return commonMerchantApplyService.detailMerchantApply(dto);
    }

    @Override
    public void editApplyCategory(CommonMerchantApplyDTO.ApplyCategoryDTO dto) {
        commonMerchantApplyService.editApplyCategory(dto);
    }

    @Override
    public List<PCMerchGoodsCategoryVO.ListVO> categoryList(CommonMerchantApplyQTO.CategoryQTO dto) {
        return commonMerchantApplyService.categoryList(dto);
    }

    @Override
    public PageData<CommonMerchantApplyVO.ApplyCategoryRecordVO> applyCategoryRecord(CommonMerchantApplyQTO.ApplyCategoryQTO qto) {
        return commonMerchantApplyService.applyCategoryRecord(qto);
    }

    @Override
    public void deleteCategoryRecord(CommonMerchantApplyDTO.IdDTO dto) {
        commonMerchantApplyService.deleteCategoryRecord(dto);
    }

    @Override
    public String innerGetLegalId(String merchantId) {
        return commonMerchantApplyService.innerGetLegalId(merchantId);
    }

    @Override
    public String innerSaveSettledApply(LegalDictDTO.SettledInfoETO eto) {
        return commonMerchantApplyService.innerSaveSettledApply(eto);
    }

    @Override
    public LegalDictVO.SettledInfoVO getAfterUpdateSettleInfo(LegalDictDTO.MerchantApplyIdDTO dto) {
        return commonMerchantApplyService.getAfterUpdateSettleInfo(dto);
    }


}