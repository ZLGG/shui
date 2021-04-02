package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRankingQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;

import java.util.List;

public interface ITradeRankingService {

    PageData<TradeRankingVO.RankingVO> pageData(TradeRankingQTO.RankingQTO qto);

    TradeSettlementVO.SaleslVO salesList(TradeSettlementQTO.SaleslQTO qto);


    //导出销售数据分析
    List<TradeSettlementVO.SaleslVO> exportSalesList(TradeSettlementQTO.SaleslQTO qo) throws Exception;
}