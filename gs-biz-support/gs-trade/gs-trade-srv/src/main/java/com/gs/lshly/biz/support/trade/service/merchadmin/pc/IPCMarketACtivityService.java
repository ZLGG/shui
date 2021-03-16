package com.gs.lshly.biz.support.trade.service.merchadmin.pc;

import com.gs.lshly.biz.support.trade.entity.MarketPtActivity;
import com.gs.lshly.common.struct.platadmin.trade.qto.MarketPtActivityQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtActivityVO;


import java.util.List;

public interface IPCMarketACtivityService {
    List<MarketPtActivityVO.ListVO> list(MarketPtActivityQTO.QTO qot);
}
