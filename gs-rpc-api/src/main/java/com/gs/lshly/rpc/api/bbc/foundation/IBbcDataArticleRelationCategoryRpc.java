package com.gs.lshly.rpc.api.bbc.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleRelationCategoryVO;

/**
*
* @author hyy
* @since 2020-11-13
*/
public interface IBbcDataArticleRelationCategoryRpc {

    PageData<BbcDataArticleRelationCategoryVO.ListVO> pageData(BbcDataArticleRelationCategoryQTO.QTO qto);

    void addDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto);

    void deleteDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto);


    void editDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.ETO eto);

    BbcDataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(BbcDataArticleRelationCategoryDTO.IdDTO dto);

}