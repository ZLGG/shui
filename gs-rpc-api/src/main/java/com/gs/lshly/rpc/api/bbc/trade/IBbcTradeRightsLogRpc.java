package com.gs.lshly.rpc.api.bbc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeRightsDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsLogQTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeRightsQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsLogVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeRightsVO;

import java.util.List;

/**
*
* @author hanly
* @since 2021-06-07
*/
public interface IBbcTradeRightsLogRpc {
    List<BbcTradeRightsLogVO.listVO> rightsLog(BbcTradeRightsLogQTO.QTO qto);
}