package com.gs.lshly.biz.support.foundation.service.bbb.h5;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5DataArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5DataArticleCategoryQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleCategoryVO;

import java.util.List;

public interface IBbbH5DataArticleCategoryService {

    List<BbbH5DataArticleCategoryVO.ListVO> list(BaseDTO dto);

    List<BbbH5DataArticleCategoryVO.SearchListVO> search(BbbH5DataArticleCategoryQTO.QTO qto);

    BbbH5DataArticleCategoryVO.DetailsVO details(BbbH5DataArticleCategoryDTO.IdDTO dto);
}