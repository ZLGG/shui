package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeMarginDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;

import java.util.List;

public interface ITradeMarginDetailService {

    PageData<TradeMarginDetailVO.ListVO> pageData(TradeMarginDetailQTO.QTO qto);

    /**
     * 导出保证金明细数据
     * @param qto
     * @return
     */
    List<TradeMarginDetailVO.ListVO> exportMarginDetailData(TradeMarginDetailQTO.IdListQTO qto);
}