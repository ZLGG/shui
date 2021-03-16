package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;

public interface IBbbSiteTopicService {

    List<BbbSiteTopicVO.ListVO> list();

}