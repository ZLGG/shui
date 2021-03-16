package com.gs.lshly.biz.support.foundation.service.bbb.h5;

import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteVideoQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteVideoVO;
import java.util.List;

public interface IBbbH5SiteVideoService {

    List<BbbH5SiteVideoVO.ListVO> video(BbbH5SiteVideoQTO.QTO qto);
}