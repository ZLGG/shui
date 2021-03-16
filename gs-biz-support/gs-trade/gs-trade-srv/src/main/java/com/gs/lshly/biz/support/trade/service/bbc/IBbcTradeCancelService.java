package com.gs.lshly.biz.support.trade.service.bbc;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCancelQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCancelVO;

public interface IBbcTradeCancelService {

    PageData<BbcTradeCancelVO.ListVO> pageData(BbcTradeCancelQTO.QTO qto);

    void addTradeCancel(BbcTradeCancelDTO.ETO eto);

    void deleteTradeCancel(BbcTradeCancelDTO.IdDTO dto);


    void editTradeCancel(BbcTradeCancelDTO.ETO eto);

    BbcTradeCancelVO.DetailVO detailTradeCancel(BbcTradeCancelDTO.IdDTO dto);

}