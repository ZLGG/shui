package com.gs.lshly.rpc.api.platadmin.foundation;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleRelationCategoryVO;

/**
*
* @author 陈奇
* @since 2020-10-19
*/
public interface IDataArticleRelationCategoryRpc {

    PageData<DataArticleRelationCategoryVO.ListVO> pageData(DataArticleRelationCategoryQTO.QTO qto);

    void addDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto);

    void deleteDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto);


    void editDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto);

    DataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto);

}