package com.gs.lshly.biz.support.trade.service.platadmin;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;

import java.util.List;

public interface ITradeService {

    PageData<TradeListVO.tradeVO> tradeListPageData(TradeQTO.TradeList qto);

    TradeListVO.tradeVO detail(TradeDTO.IdDTO dto);
    List<TradeVO.ListVOExport> export(TradeQTO.IdListQTO qo);

}