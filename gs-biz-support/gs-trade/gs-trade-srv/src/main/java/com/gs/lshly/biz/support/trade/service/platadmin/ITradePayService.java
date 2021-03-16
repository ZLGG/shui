package com.gs.lshly.biz.support.trade.service.platadmin;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;

import java.util.List;

public interface ITradePayService {

    PageData<TradePayVO.ListVO> pageData(TradePayQTO.QTO qto);

    TradePayVO.DetailVO detailTradePay(TradePayDTO.IdDTO dto);

    PageData<TradePayVO.RelationDetailVO> relationList(TradePayQTO.RelationQTO qto);

    TradePayVO.DetailVO relationGet(TradePayVO.DetailVO detailVO);


    List<TradePayVO.RelationDetailVO> export(TradePayDTO.IdsDTO qo);
}