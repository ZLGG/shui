package com.gs.lshly.biz.support.foundation.service.bbc;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;

import java.util.List;

public interface IBbcSiteVideoService {

    List<BbcSiteVideoVO.ListVO> list(BbcSiteVideoQTO.QTO qto);

    BbcSiteVideoVO.ListVO video(BbcSiteVideoQTO.QTO qto);
}