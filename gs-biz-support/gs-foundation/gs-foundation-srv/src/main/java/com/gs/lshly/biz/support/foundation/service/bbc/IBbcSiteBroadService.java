package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBroadDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBroadQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBroadVO;

import java.util.List;

public interface IBbcSiteBroadService {

    List<BbcSiteBroadVO.ListVO> list(BbcSiteBroadQTO.QTO qto);

    BbcSiteBroadVO.DetailsVO details(BbcSiteBroadDTO.IdDTO dto);

}