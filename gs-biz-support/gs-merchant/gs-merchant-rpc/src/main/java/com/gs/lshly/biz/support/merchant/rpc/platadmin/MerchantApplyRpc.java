package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantApplyVO;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantApplyService;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantApplyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-14
*/
@DubboService
public class MerchantApplyRpc implements IMerchantApplyRpc{

    @Autowired
    private IMerchantApplyService merchantApplyService;

    @Override
    public PageData<MerchantApplyVO.ListVO> pageData(MerchantApplyQTO.QTO qto){
        return merchantApplyService.pageData(qto);
    }

    @Override
    public PageData<MerchantApplyVO.ListVO> pageSearch(MerchantApplyQTO.SearchQTO qto) {
        return merchantApplyService.pageSearch(qto);
    }

    @Override
    public void deleteBatchMerchantApply(MerchantApplyDTO.IdListDTO dto) {
        merchantApplyService.deleteBatchMerchantApply(dto);
    }

    @Override
    public void apply(MerchantApplyDTO.ApplyDTO dto) {
        merchantApplyService.apply(dto);
    }

    @Override
    public void checkEditApply(MerchantApplyDTO.CheckApplyDTO dto) {
        merchantApplyService.checkEditApply(dto);
    }

    @Override
    public void openShop(MerchantApplyDTO.IdDTO dto) {
        merchantApplyService.openShop(dto);
    }

    @Override
    public MerchantApplyVO.BrandVO handBrandQuery(MerchantApplyDTO.IdDTO dto) {
        return merchantApplyService.handBrandQuery(dto);
    }


    @Override
    public void handBrandSubmit(MerchantApplyDTO.HandBrandDTO dto) {
        merchantApplyService.handBrandSubmit(dto);
    }

    @Override
    public MerchantApplyVO.DetailVO detailMerchantApply(MerchantApplyDTO.IdDTO dto){
        return merchantApplyService.detailMerchantApply(dto);
    }

}