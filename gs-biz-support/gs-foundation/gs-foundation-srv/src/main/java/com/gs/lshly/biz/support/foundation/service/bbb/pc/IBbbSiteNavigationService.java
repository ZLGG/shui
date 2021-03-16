package com.gs.lshly.biz.support.foundation.service.bbb.pc;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNavigationQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNavigationVO;

public interface IBbbSiteNavigationService {

    BbbSiteNavigationVO.HomeVO homeDetail(PCBbbGoodsCategoryQTO.QTO qto);

    BbbSiteNavigationVO.DetailVO subjectDetail(BbbSiteNavigationQTO.BQTO qto);

}