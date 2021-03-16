package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteBannerVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;

import java.util.List;

public interface IBbbSiteBannerService {

    List<BbbSiteBannerVO.ListVO> list(BbbSiteBannerQTO.QTO qto);
}