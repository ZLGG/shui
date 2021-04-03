package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import java.util.List;

import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO;

public interface IBbbSiteNoticeService {

    List<BbbSiteNoticeVO.ListVO> list(BbbSiteNoticeQTO.QTO qto);
    
    
    BbbSiteNoticeVO.DetailVO detail(BbbSiteNoticeQTO.IDQTO qto);

}