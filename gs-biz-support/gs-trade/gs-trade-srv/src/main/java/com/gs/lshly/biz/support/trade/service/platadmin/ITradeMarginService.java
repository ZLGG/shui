package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginVO;

import java.math.BigDecimal;

public interface ITradeMarginService {

    PageData<TradeMarginVO.ListVO> pageData(TradeMarginQTO.marginQTO qto);

    void updateByQuota(TradeMarginDTO.QuotaDTO eto);

    void updateByCharge(TradeMarginDTO.ChargeDTO eto);

    void updateByDeduction(TradeMarginDTO.DeductionDTO eto);

    TradeMarginVO.DetailVO detailTradeMargin(TradeMarginDTO.IdDTO dto);

    void InnerCreateShopMargin(TradeMarginDTO.InnerCreateMargin dto);

}