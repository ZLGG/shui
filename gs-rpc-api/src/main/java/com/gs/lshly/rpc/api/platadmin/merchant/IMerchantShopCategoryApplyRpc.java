package com.gs.lshly.rpc.api.platadmin.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantShopCategoryApplyDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantShopCategoryApplyQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantShopCategoryApplyVO;

/**
*
* @author xxfc
* @since 2020-10-16
*/
public interface IMerchantShopCategoryApplyRpc {

    PageData<MerchantShopCategoryApplyVO.ListVO> pageData(MerchantShopCategoryApplyQTO.QTO qto);

    MerchantShopCategoryApplyVO.DetailVO details(MerchantShopCategoryApplyDTO.IdDTO dto);

    void deleteBatchMerchantShopCategoryApply(MerchantShopCategoryApplyDTO.IdListDTO dto);

    void apply(MerchantShopCategoryApplyDTO.ApplyDTO dto);

}