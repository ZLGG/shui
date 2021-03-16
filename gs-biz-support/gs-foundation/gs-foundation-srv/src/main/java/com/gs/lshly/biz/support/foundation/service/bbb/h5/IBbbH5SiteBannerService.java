package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBannerQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBannerVO;

import java.util.List;

public interface IBbbH5SiteBannerService {

    List<BbbH5SiteBannerVO.ListVO> list(BbbH5SiteBannerQTO.QTO qto);
}