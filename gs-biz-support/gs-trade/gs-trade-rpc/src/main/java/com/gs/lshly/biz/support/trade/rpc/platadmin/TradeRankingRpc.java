package com.gs.lshly.biz.support.trade.rpc.platadmin;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeSettlementService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeRankingDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeRankingQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeRankingVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRankingRpc;
import com.gs.lshly.biz.support.trade.service.platadmin.ITradeRankingService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
*
* @author zst
* @since 2020-12-18
*/
@DubboService
public class TradeRankingRpc implements ITradeRankingRpc{
    @Autowired
    private ITradeRankingService  TradeRankingService;

    @Override
    public PageData<TradeRankingVO.RankingVO> pageData(TradeRankingQTO.RankingQTO qto){
        return TradeRankingService.pageData(qto);
    }

    @Override
    public TradeSettlementVO.SaleslVO salesList(TradeSettlementQTO.SaleslQTO qto){
        return TradeRankingService.salesList(qto);
    }
}