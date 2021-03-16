package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcDataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcDataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleCategoryVO;

import java.util.List;

public interface IBbcDataArticleCategoryService {

    List<BbcDataArticleCategoryVO.ListVO> list(BaseDTO dto);

    List<BbcDataArticleCategoryVO.SearchListVO> search(BbcDataArticleCategoryQTO.QTO qto);

    BbcDataArticleCategoryVO.DetailsVO details(BbcDataArticleCategoryDTO.IdDTO dto);
}