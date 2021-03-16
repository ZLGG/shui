package com.gs.lshly.biz.support.trade.rpc.bbb.h5;

import com.gs.lshly.biz.support.trade.service.bbb.h5.IBbbH5TradeService;
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
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
*
* @author oy
* @since 2020-10-28
*/
@DubboService
public class BbbH5TradeRpc implements IBbbH5TradeRpc {
    
    @Autowired
    private IBbbH5TradeService bbcTradeService;

    @Override
    public ResponseData<BbbH5TradeSettlementVO.ListVO> settlementVO(BbbH5TradeBuildDTO.cartIdsDTO dto) {
        return bbcTradeService.settlementVO(dto);
    }

    @Override
    public ResponseData<BbbH5TradeDTO.IdDTO> orderSubmit(BbbH5TradeBuildDTO.DTO dto) {
        return bbcTradeService.orderSubmit(dto);
    }

    @Override
    public ResponseData<Void> orderPay(BbbH5TradePayBuildDTO.ETO dto) {
        return bbcTradeService.orderPay(dto);
    }

    @Override
    public PageData<BbbH5TradeListVO.tradeVO> tradeListPageData(BbbH5TradeQTO.TradeList qto) {
        return bbcTradeService.tradeListPageData(qto);
    }

    @Override
    public ResponseData<BbbH5TradeListVO.tradeVO> orderDetail(BbbH5TradeDTO.IdDTO dto) {
        return bbcTradeService.orderDetail(dto);
    }

    @Override
    public ResponseData<Void> orderConfirmReceipt(BbbH5TradeDTO.IdDTO dto) {

        return bbcTradeService.orderConfirmReceipt(dto);
    }

    @Override
    public ResponseData<Void> deliveryAmount( BbbH5TradeBuildDTO.DTO dto) {
        return bbcTradeService.deliveryAmount(dto);
    }

    @Override
    public String payNotify(BbbH5TradeResultNotifyVO.notifyVO notifyVO) {
        return bbcTradeService.payNotify(notifyVO);
    }

    @Override
    public String paySuccess(String tradeCode) {
        return bbcTradeService.paySuccess(tradeCode);
    }

    @Override
    public ResponseData<Void> orderHide(BbbH5TradeDTO.IdDTO dto) {
        return bbcTradeService.orderHide(dto);
    }

    @Override
    public ResponseData<Void> orderCancel(BbbH5TradeCancelDTO.CancelDTO dto) {
        return bbcTradeService.orderCancel(dto);
    }

    @Override
    public List<BbbH5TradeListVO.stateCountVO> tradeStateCount(BbbH5TradeDTO.IdDTO dto) {
        return bbcTradeService.tradeStateCount(dto);
    }

    @Override
    public List<BbbH5TradeListVO.UseCard> useCard(BbbH5TradeDTO.UseCard dto) {
        return bbcTradeService.useCard(dto);
    }

    @Override
    public void deleteTrade(BbbH5TradeDTO.IdDTO dto) {
        bbcTradeService.deleteTrade(dto);
    }

    @Override
    public List<BbbH5TradeListVO.InnerGoodsCompareTo> innerCommpareTo(BbbH5TradeDTO.innerCommpareTo dto) {
        return bbcTradeService.innerCommpareTo(dto);
    }

    @Override
    public Integer myMerchantCard(BaseDTO dto) {
        return bbcTradeService.myMerchantCard(dto);
    }

}