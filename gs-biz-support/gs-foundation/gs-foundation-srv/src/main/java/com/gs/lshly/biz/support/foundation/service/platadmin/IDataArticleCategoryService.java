package com.gs.lshly.biz.support.foundation.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.DataArticleCategoryDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.DataArticleCategoryQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.DataArticleCategoryVO;

import java.util.List;

public interface IDataArticleCategoryService {

    List<DataArticleCategoryVO.FirstListVO> pageData(DataArticleCategoryQTO.FirstQTO qto);

    void addDataArticleCategory(DataArticleCategoryDTO.ADTO adto);

    void deleteDataArticleCategory(DataArticleCategoryDTO.IdDTO dto);

    void editDataArticleCategory(DataArticleCategoryDTO.UDTO udto);

    PageData<DataArticleCategoryVO.SecondListVO> pageData2(DataArticleCategoryQTO.SecondQTO qto);

    PageData<DataArticleCategoryVO.SecondListVO> pageData3(DataArticleCategoryQTO.SecondQTO qto);

    void changeIdx(List<DataArticleCategoryDTO.IdxDTO> idxDTOS);
}