package com.gs.lshly.rpc.api.platadmin.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;

/**
*
* @author zst
* @since 2020-12-01
*/
public interface ITradeSettlementDetailRpc {

    PageData<TradeSettlementDetailVO.ListVO> pageData(TradeSettlementDetailQTO.ListQTO qto);

    void addTradeSettlementDetail(TradeSettlementDetailDTO.ETO eto);

    TradeSettlementDetailVO.DetailVO detailTradeSettlementDetail(TradeSettlementDetailDTO.IdDTO dto);

    ExportDataDTO export(TradeSettlementDetailQTO.ListQTO qo) throws Exception;

}