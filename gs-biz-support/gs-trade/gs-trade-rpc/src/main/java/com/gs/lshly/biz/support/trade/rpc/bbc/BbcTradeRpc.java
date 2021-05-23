package com.gs.lshly.biz.support.trade.rpc.bbc;

import com.gs.lshly.biz.support.trade.service.bbc.IBbcTradeService;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO.CheckAndPointDoPayETO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author oy
* @since 2020-10-28
*/
@DubboService
public class BbcTradeRpc implements IBbcTradeRpc{
    @Autowired
    private IBbcTradeService  bbcTradeService;

    @Override
    public ResponseData<BbcTradeSettlementVO.DetailVO> settlementVO(BbcTradeBuildDTO.cartIdsDTO dto) {
        return bbcTradeService.settlementVO(dto);
    }

    @Override
    public ResponseData<BbcTradeDTO.ListIdDTO> orderSubmit(BbcTradeBuildDTO.DTO dto) {
        return bbcTradeService.orderSubmit(dto);
    }

    @Override
    public ResponseData<Void> orderPay(BbcTradePayBuildDTO.ETO dto) {
        return bbcTradeService.orderPay(dto);
    }

    @Override
    public PageData<BbcTradeListVO.tradeVO> tradeListPageData(BbcTradeQTO.TradeList qto) {
        return bbcTradeService.tradeListPageData(qto);
    }

    @Override
    public ResponseData<BbcTradeListVO.tradeVO> orderDetail(BbcTradeDTO.IdDTO dto) {
        return bbcTradeService.orderDetail(dto);
    }

    @Override
    public ResponseData<Void> orderConfirmReceipt(BbcTradeDTO.IdDTO dto) {

        return bbcTradeService.orderConfirmReceipt(dto);
    }

    @Override
    public ResponseData<Void> deliveryAmount( BbcTradeBuildDTO.DTO dto) {
        return bbcTradeService.deliveryAmount(dto);
    }

    @Override
    public String payNotify(BbcTradeResultNotifyVO.notifyVO notifyVO) {
        return bbcTradeService.payNotify(notifyVO);
    }

    @Override
    public String paySuccess(String tradeCode) {
        return bbcTradeService.paySuccess(tradeCode);
    }

    @Override
    public ResponseData<Void> orderHide(BbcTradeDTO.IdDTO dto) {
        return bbcTradeService.orderHide(dto);
    }

    @Override
    public ResponseData<Void> orderCancel(BbcTradeCancelDTO.CancelDTO dto) {
        return bbcTradeService.orderCancel(dto);
    }

    @Override
    public List<BbcTradeListVO.stateCountVO> tradeStateCount(BbcTradeDTO.IdDTO dto) {
        return bbcTradeService.tradeStateCount(dto);
    }
    @Override
    public List<BbcTradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbcTradeDTO.innerCommpareTo dto) {
        return bbcTradeService.innerCommpareTo(dto);
    }

    @Override
    public PageData<BbcTradeListVO.PageData> myUserCard(BbcTradeQTO.UserCardQTO qto) {
        return bbcTradeService.myUserCard(qto);
    }

    @Override
    public List<BbcTradeListVO.UseCard> useCard(BbcTradeDTO.UseCard dto) {
        return bbcTradeService.useCard(dto);
    }

    @Override
    public void offlinePay(BbcTradeDTO.OfflinePayDTO dto) {
        bbcTradeService.offlinePay(dto);
    }

    @Override
    public int innerMonthSaleNum(String goodsId) {
        return bbcTradeService.innerMonthSaleNum(goodsId);
    }

    @Override
    public void modifyOrderAddress(BbcTradeDTO.ModifyOrderAddressDTO dto) {
        bbcTradeService.modifyOrderAddress(dto);
    }

    @Override
    public Integer getSaleQuantity(String id) {
        return bbcTradeService.getSaleQuantity(id);
    }

    @Override
    public Integer myMerchantCard(BaseDTO dto) {
        return bbcTradeService.myMerchantCard(dto);
    }

	@Override
	public ResponseData<Void> checkAndPointDoPay(CheckAndPointDoPayETO dto) {
		return bbcTradeService.checkAndPointDoPay(dto);
	}

}