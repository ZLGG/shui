package com.gs.lshly.biz.support.foundation.service.bbb.h5;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5DataArticleVO;

import java.util.List;

public interface IBbbH5DataArticleService {

    List<BbbH5DataArticleVO.ListVO> list(BaseDTO dto);

}