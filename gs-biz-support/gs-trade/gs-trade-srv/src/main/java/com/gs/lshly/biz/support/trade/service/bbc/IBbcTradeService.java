package com.gs.lshly.biz.support.trade.service.bbc;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;

import java.util.List;

public interface IBbcTradeService {

    ResponseData<BbcTradeSettlementVO.DetailVO> settlementVO(BbcTradeBuildDTO.cartIdsDTO dto);

    ResponseData<BbcTradeDTO.ListIdDTO> orderSubmit(BbcTradeBuildDTO.DTO dto);

    ResponseData<Void> orderPay(BbcTradePayBuildDTO.ETO dto);
    
    /**
     * 手机号码验证+第三方验证
     * @param dto
     * @return
     */
    ResponseData<Void> checkAndPointDoPay(BbcTradePayBuildDTO.CheckAndPointDoPayETO dto);

    PageData<BbcTradeListVO.tradeVO> tradeListPageData(BbcTradeQTO.TradeList qto);

    ResponseData<BbcTradeListVO.tradeVO> orderDetail(BbcTradeDTO.IdDTO dto);

    ResponseData<Void> orderConfirmReceipt(BbcTradeDTO.IdDTO dto);

    ResponseData<Void> deliveryAmount(BbcTradeBuildDTO.DTO dto);

    String payNotify(BbcTradeResultNotifyVO.notifyVO notifyVO);

    String paySuccess(String tradeCode);

    ResponseData<Void> orderHide(BbcTradeDTO.IdDTO dto);

    ResponseData<Void> orderCancel(BbcTradeCancelDTO.CancelDTO dto);

    List<BbcTradeListVO.stateCountVO> tradeStateCount(BbcTradeDTO.IdDTO dto);

    List<BbcTradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbcTradeDTO.innerCommpareTo dto);

    PageData<BbcTradeListVO.PageData> myUserCard(BbcTradeQTO.UserCardQTO qto);

    List<BbcTradeListVO.UseCard> useCard(BbcTradeDTO.UseCard dto);

    void offlinePay(BbcTradeDTO.OfflinePayDTO dto);

    /**
     * 商品的月销售数量
     * @param goodsId
     * @return
     */
    int innerMonthSaleNum(String goodsId);

    Integer myMerchantCard(BaseDTO dto);

    /**
     * 获取商品数量
     * @param id
     * @param code
     * @return
     */
    Integer getSaleQuantity(String id);

    /**
     * 获取积分兑换数量
     * @param id
     * @return
     */
    Integer getExchangeQuantity(String id);

    /**
     * 修改订单收货地址
     * @param dto
     */
    void modifyOrderAddress(BbcTradeDTO.ModifyOrderAddressDTO dto);
    
}