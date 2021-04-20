package com.gs.lshly.biz.support.foundation.service.bbc;
import java.util.List;

import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO;

public interface IBbcPointHomeService {

	List<BbcPointHomePageVO.ListVO> getHome(BbcPointHomeQTO.QTO qto);
	
}