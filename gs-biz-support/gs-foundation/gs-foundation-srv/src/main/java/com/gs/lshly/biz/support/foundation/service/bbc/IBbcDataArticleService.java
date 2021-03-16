package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcDataArticleVO;

import java.util.List;

public interface IBbcDataArticleService {

    List<BbcDataArticleVO.ListVO> list(BaseDTO dto);

}