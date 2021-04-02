package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
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

    //导出商品点击排行分析
    ExportDataDTO export(TradeRankingQTO.RankingQTO qo) throws Exception;

    //导出销售数据分析
    ExportDataDTO exportSalesList(TradeSettlementQTO.SaleslQTO qo) throws Exception;}