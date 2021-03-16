package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.MerchantArticleCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.MerchantArticleCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.MerchantArticleCategoryVO;

public interface IMerchantArticleCategoryService {

    PageData<MerchantArticleCategoryVO.ListVO> pageData(MerchantArticleCategoryQTO.QTO qto);

    void addMerchantArticleCategory(MerchantArticleCategoryDTO.ETO eto);

    void deleteMerchantArticleCategory(MerchantArticleCategoryDTO.IdDTO dto);


    void editMerchantArticleCategory(MerchantArticleCategoryDTO.ETO eto);

    MerchantArticleCategoryVO.DetailVO detailMerchantArticleCategory(MerchantArticleCategoryDTO.IdDTO dto);

}