package com.gs.lshly.biz.support.trade.service.bbb.h5;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCancelDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradePayBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeResultNotifyVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeSettlementVO;
import java.util.List;

public interface IBbbH5TradeService {

    ResponseData<BbbH5TradeSettlementVO.ListVO> settlementVO(BbbH5TradeBuildDTO.cartIdsDTO dto);

    ResponseData<BbbH5TradeDTO.IdDTO> orderSubmit(BbbH5TradeBuildDTO.DTO dto);

    ResponseData<Void> orderPay(BbbH5TradePayBuildDTO.ETO dto);

    PageData<BbbH5TradeListVO.tradeVO> tradeListPageData(BbbH5TradeQTO.TradeList qto);

    ResponseData<BbbH5TradeListVO.tradeVO> orderDetail(BbbH5TradeDTO.IdDTO dto);

    ResponseData<Void> orderConfirmReceipt(BbbH5TradeDTO.IdDTO dto);

    ResponseData<Void> deliveryAmount(BbbH5TradeBuildDTO.DTO dto);

    String payNotify(BbbH5TradeResultNotifyVO.notifyVO notifyVO);

    String paySuccess(String tradeCode);

    ResponseData<Void> orderHide(BbbH5TradeDTO.IdDTO dto);

    ResponseData<Void> orderCancel(BbbH5TradeCancelDTO.CancelDTO dto);

    List<BbbH5TradeListVO.stateCountVO> tradeStateCount(BbbH5TradeDTO.IdDTO dto);

    List<BbbH5TradeListVO.UseCard> useCard(BbbH5TradeDTO.UseCard dto);

    void deleteTrade(BbbH5TradeDTO.IdDTO dto);

    List<BbbH5TradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbbH5TradeDTO.innerCommpareTo dto);

    Integer myMerchantCard(BaseDTO dto);

    void offlinePay(BbbH5TradeDTO.OfflinePayDTO dto);

    ResponseData<BbbH5TradeListVO.OfflinePayVO> offlineDetail(BbbH5TradeDTO.IdDTO dto);
}