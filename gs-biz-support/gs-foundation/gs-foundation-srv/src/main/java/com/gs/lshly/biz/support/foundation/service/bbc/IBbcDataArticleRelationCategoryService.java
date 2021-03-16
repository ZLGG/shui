package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleRelationCategoryVO;

public interface IBbcDataArticleRelationCategoryService {

    PageData<BbcDataArticleRelationCategoryVO.ListVO> pageData(BbcDataArticleRelationCategoryQTO.QTO qto);

    void addDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto);

    void deleteDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto);


    void editDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto);

    BbcDataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto);

}