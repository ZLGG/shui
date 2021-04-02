package com.gs.lshly.biz.support.merchant.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantShopCategoryApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantShopCategoryApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantShopCategoryApplyVO;

import java.util.List;

public interface IMerchantShopCategoryApplyService {

    PageData<MerchantShopCategoryApplyVO.ListVO> pageData(MerchantShopCategoryApplyQTO.QTO qto);

    MerchantShopCategoryApplyVO.DetailVO details(MerchantShopCategoryApplyDTO.IdDTO dto);

    void deleteBatchMerchantShopCategoryApply(MerchantShopCategoryApplyDTO.IdListDTO dto);

    void apply(MerchantShopCategoryApplyDTO.ApplyDTO dto);

    /**
     * 内部服务
     * @param applyId
     * @return
     */
    List<String> innerGetApplyCategoryIdList(String applyId);

}