package com.gs.lshly.biz.support.trade.service.bbb.pc;


import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbPcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCancelDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbOrderQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;

import java.util.List;

public interface IBbbPcTradeService {

    ResponseData<BbbTradeSettlementVO.ListVO> settlementVO(BbbTradeBuildDTO.cartIdsDTO dto);

    ResponseData<BbbOrderDTO.IdDTO> orderSubmit(BbbTradeBuildDTO.DTO dto);

    PageData<BbbTradeListVO.tradeVO> tradeListPageData(BbbOrderQTO.TradeList qto);

    ResponseData<BbbTradeListVO.tradeVO> orderDetail(BbbOrderDTO.IdDTO dto);

    ResponseData<Void> orderCancel(BbbTradeCancelDTO.CancelDTO dto);

    ResponseData<Void> orderConfirmReceipt(BbbOrderDTO.IdDTO dto);

    ResponseData<Void> orderPay(BbbPcTradePayBuildDTO.ETO dto);

    boolean isFinishedPay(String tradeId,String userCard);

    String payNotify(BbcTradeResultNotifyVO.notifyVO notifyVO);

   void offlinePay(BbbOrderDTO.OfflinePayDTO dto);

    ResponseData<BbbTradeListVO.OfflinePayVO> offlineDetail(BbbOrderDTO.IdDTO dto);

    List<BbbTradeListVO.tradeVO> latelyTrade(BbbOrderDTO.StateDTO dto);

    ResponseData<List<BbbTradeListVO.tradeVO>> newTrade();

    List<BbbTradeListVO.UseCard> useCard(BbbOrderDTO.UseCard dto);

    List<BbbTradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbbOrderDTO.innerCommpareTo dto);

    BbbTradeListVO.InnerGoodsScore innerShopScore(String shopId, String id);

}
