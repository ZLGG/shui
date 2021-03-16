package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRankingQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

/**
*
* @author zst
* @since 2020-12-18
*/
public interface ITradeRankingRpc {

    PageData<TradeRankingVO.RankingVO> pageData(TradeRankingQTO.RankingQTO qto);

    TradeSettlementVO.SaleslVO salesList(TradeSettlementQTO.SaleslQTO qto);
}