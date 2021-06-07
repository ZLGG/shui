package com.gs.lshly.biz.support.trade.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsLogQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsLogVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;

import java.util.List;

public interface IBbcTradeRightsLogService {
    List<BbcTradeRightsLogVO.listVO> rightsLog(BbcTradeRightsLogQTO.QTO qto);
}