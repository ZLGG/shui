package com.gs.lshly.biz.support.merchant.rpc.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantShopCategoryApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantShopCategoryApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantShopCategoryApplyVO;
import com.gs.lshly.biz.support.merchant.service.platadmin.IMerchantShopCategoryApplyService;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantShopCategoryApplyRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author xxfc
* @since 2020-10-16
*/
@DubboService
public class MerchantShopCategoryApplyRpc implements IMerchantShopCategoryApplyRpc{

    @Autowired
    private IMerchantShopCategoryApplyService merchantShopCategoryApplyService;

    @Override
    public PageData<MerchantShopCategoryApplyVO.ListVO> pageData(MerchantShopCategoryApplyQTO.QTO qto){
        return merchantShopCategoryApplyService.pageData(qto);
    }

    @Override
    public MerchantShopCategoryApplyVO.DetailVO details(MerchantShopCategoryApplyDTO.IdDTO dto) {
        return merchantShopCategoryApplyService.details(dto);
    }

    @Override
    public void deleteBatchMerchantShopCategoryApply(MerchantShopCategoryApplyDTO.IdListDTO dto) {
        merchantShopCategoryApplyService.deleteBatchMerchantShopCategoryApply(dto);
    }

    @Override
    public void apply(MerchantShopCategoryApplyDTO.ApplyDTO dto) {
        merchantShopCategoryApplyService.apply(dto);
    }

    @Override
    public List<String> innerGetApplyCategoryIdList(String applyId) {
        return merchantShopCategoryApplyService.innerGetApplyCategoryIdList(applyId);
    }


}