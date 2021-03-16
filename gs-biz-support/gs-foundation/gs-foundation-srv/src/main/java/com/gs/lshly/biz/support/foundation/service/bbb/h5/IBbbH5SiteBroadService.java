package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.foundation.dto.BbbH5SiteBroadDTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBroadQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBroadVO;

import java.util.List;

public interface IBbbH5SiteBroadService {

    List<BbbH5SiteBroadVO.ListVO> list(BbbH5SiteBroadQTO.QTO qto);

    BbbH5SiteBroadVO.DetailsVO details(BbbH5SiteBroadDTO.IdDTO dto);

}