package com.gs.lshly.rpc.api.bbb.h5.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleRelationCategoryVO;

/**
*
* @author hyy
* @since 2020-11-13
*/
public interface IBbbH5DataArticleRelationCategoryRpc {

    PageData<BbbH5DataArticleRelationCategoryVO.ListVO> pageData(BbbH5DataArticleRelationCategoryQTO.QTO qto);

    void addDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.ETO eto);

    void deleteDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.IdDTO dto);


    void editDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.ETO eto);

    BbbH5DataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(BbbH5DataArticleRelationCategoryDTO.IdDTO dto);

}