package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteNavigationVO;
import java.util.List;

public interface IBbbH5SiteNavigationService {

    List<BbbH5SiteNavigationVO.ListVO> list(BbbH5SiteNavigationQTO.QTO qto);
}