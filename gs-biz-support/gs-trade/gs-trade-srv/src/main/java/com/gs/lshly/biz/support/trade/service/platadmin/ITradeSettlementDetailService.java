package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;

public interface ITradeSettlementDetailService {

    PageData<TradeSettlementDetailVO.ListVO> pageData(TradeSettlementDetailQTO.ListQTO qto);

    void addTradeSettlementDetail(TradeSettlementDetailDTO.ETO eto);

    TradeSettlementDetailVO.DetailVO detailTradeSettlementDetail(TradeSettlementDetailDTO.IdDTO dto);

}