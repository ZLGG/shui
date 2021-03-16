package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.dto.BbcSiteBannerDTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;

import java.util.List;

public interface IBbcSiteBannerService {

    List<BbcSiteBannerVO.ListVO> list(BbcSiteBannerQTO.QTO qto);
}