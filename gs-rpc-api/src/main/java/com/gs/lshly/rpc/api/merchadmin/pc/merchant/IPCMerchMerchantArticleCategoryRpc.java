package com.gs.lshly.rpc.api.merchadmin.pc.merchant;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleCategoryVO;

/**
*
* @author hyy
* @since 2020-11-07
*/
public interface IPCMerchMerchantArticleCategoryRpc {

    PageData<PCMerchMerchantArticleCategoryVO.ListVO> pageData(PCMerchMerchantArticleCategoryQTO.QTO qto);

    void addMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto);

    void deleteMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.IdDTO dto);

    void editMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto);

}