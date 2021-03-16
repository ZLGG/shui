package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleRelationCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleRelationCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleRelationCategoryVO;

public interface IDataArticleRelationCategoryService {

    PageData<DataArticleRelationCategoryVO.ListVO> pageData(DataArticleRelationCategoryQTO.QTO qto);

    void addDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto);

    void deleteDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto);


    void editDataArticleRelationCategory(DataArticleRelationCategoryDTO.ETO eto);

    DataArticleRelationCategoryVO.DetailVO detailDataArticleRelationCategory(DataArticleRelationCategoryDTO.IdDTO dto);

}