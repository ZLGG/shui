package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteNavigationDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNavigationQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteNavigationVO;

import java.util.List;

public interface IBbcSiteNavigationService {

    List<BbcSiteNavigationVO.ListVO> list(BbcSiteNavigationQTO.QTO qto);
}