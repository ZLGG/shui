package com.gs.lshly.biz.support.trade.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCancelDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeCancelQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeCancelVO;

public interface IBbbH5TradeCancelService {

    PageData<BbbH5TradeCancelVO.ListVO> pageData(BbbH5TradeCancelQTO.QTO qto);

    void addTradeCancel(BbbH5TradeCancelDTO.ETO eto);

    void deleteTradeCancel(BbbH5TradeCancelDTO.IdDTO dto);


    void editTradeCancel(BbbH5TradeCancelDTO.ETO eto);

    BbbH5TradeCancelVO.DetailVO detailTradeCancel(BbbH5TradeCancelDTO.IdDTO dto);

}