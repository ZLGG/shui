package com.gs.lshly.rpc.api.bbc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeCancelQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeCancelVO;

/**
*
* @author oy
* @since 2020-11-20
*/
public interface IBbcTradeCancelRpc {

    PageData<BbcTradeCancelVO.ListVO> pageData(BbcTradeCancelQTO.QTO qto);

    void addTradeCancel(BbcTradeCancelDTO.ETO eto);

    void deleteTradeCancel(BbcTradeCancelDTO.IdDTO dto);


    void editTradeCancel(BbcTradeCancelDTO.ETO eto);

    BbcTradeCancelVO.DetailVO detailTradeCancel(BbcTradeCancelDTO.IdDTO dto);

}