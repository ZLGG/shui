package com.gs.lshly.biz.support.merchant.service.merchadmin.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantArticleCategoryVO;

public interface IPCMerchMerchantArticleCategoryService {

    PageData<PCMerchMerchantArticleCategoryVO.ListVO> pageData(PCMerchMerchantArticleCategoryQTO.QTO qto);

    void addMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto);

    void deleteMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.IdDTO dto);

    void editMerchantArticleCategory(PCMerchMerchantArticleCategoryDTO.ETO eto);
}