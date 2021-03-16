package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteAdvertVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;

import java.util.List;

public interface IBbbSiteAdvertService {

    PageData<BbbSiteAdvertVO.SubjectListVO> pageList(BbbSiteAdvertQTO.QTO qto);

}