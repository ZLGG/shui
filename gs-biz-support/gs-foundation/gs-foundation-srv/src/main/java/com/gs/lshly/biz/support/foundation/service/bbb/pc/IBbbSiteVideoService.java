package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;

import java.util.List;

public interface IBbbSiteVideoService {

    List<BbbSiteVideoVO.ListVO> list(BbbSiteVideoQTO.QTO qto);
}