package com.gs.lshly.biz.support.trade.rpc.bbb.pc;

import com.gs.lshly.biz.support.trade.service.bbb.pc.IBbbPcTradeService;
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
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbPcTradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author TANGXU
* @since 2020-12-15
*/
@DubboService
public class BbbPcTradeRpc implements IBbbPcTradeRpc {

    @Autowired
    private IBbbPcTradeService iBbbPcTradeService;

    @Override
    public ResponseData<Void> orderPay(BbbPcTradePayBuildDTO.ETO dto) {
        return iBbbPcTradeService.orderPay(dto);
    }

    @Override
    public String payNotify(BbcTradeResultNotifyVO.notifyVO notifyVO) {
        return iBbbPcTradeService.payNotify(notifyVO);
    }

    @Override
    public void offlinePay(BbbOrderDTO.OfflinePayDTO dto) {
        iBbbPcTradeService.offlinePay(dto);
    }

    @Override
    public ResponseData<BbbTradeListVO.OfflinePayVO> offlineDetail(BbbOrderDTO.IdDTO dto) {
        return iBbbPcTradeService.offlineDetail(dto);
    }

    @Override
    public List<BbbTradeListVO.tradeVO> latelyTrade(BbbOrderDTO.StateDTO dto) {
        return iBbbPcTradeService.latelyTrade(dto);
    }

    @Override
    public ResponseData<List<BbbTradeListVO.tradeVO>> newTrade() {
        return iBbbPcTradeService.newTrade();
    }

    @Override
    public List<BbbTradeListVO.UseCard> useCard(BbbOrderDTO.UseCard dto) {
        return iBbbPcTradeService.useCard(dto);
    }

    @Override
    public List<BbbTradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbbOrderDTO.innerCommpareTo dto) {
        return iBbbPcTradeService.innerCommpareTo(dto);
    }

    @Override
    public boolean isFinishedPay(String tradeId,String userCard) {
        return iBbbPcTradeService.isFinishedPay(tradeId,userCard);
    }

    @Override
    public ResponseData<BbbTradeSettlementVO.ListVO> settlementVO(BbbTradeBuildDTO.cartIdsDTO dto) {
        return iBbbPcTradeService.settlementVO(dto);
    }

    @Override
    public ResponseData<BbbOrderDTO.IdDTO> orderSubmit(BbbTradeBuildDTO.DTO dto) {
        return iBbbPcTradeService.orderSubmit(dto);
    }

    @Override
    public PageData<BbbTradeListVO.tradeVO> tradeListPageData(BbbOrderQTO.TradeList qto) {
        return iBbbPcTradeService.tradeListPageData(qto);
    }

    @Override
    public ResponseData<BbbTradeListVO.tradeVO> orderDetail(BbbOrderDTO.IdDTO dto) {
        return iBbbPcTradeService.orderDetail(dto);
    }

    @Override
    public ResponseData<Void> orderCancel(BbbTradeCancelDTO.CancelDTO dto) {
        return iBbbPcTradeService.orderCancel(dto);
    }

    @Override
    public ResponseData<Void> orderConfirmReceipt(BbbOrderDTO.IdDTO dto) {
        return iBbbPcTradeService.orderConfirmReceipt(dto);
    }
}