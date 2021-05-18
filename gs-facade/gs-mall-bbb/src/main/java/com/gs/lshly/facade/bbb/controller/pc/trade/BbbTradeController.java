package com.gs.lshly.facade.bbb.controller.pc.trade;
import java.util.List;

import javax.validation.Valid;

import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author oy
 * @since 2020-10-28
 */
@RestController
@RequestMapping("/bbb")
@Api(tags = "交易订单管理")
public class BbbTradeController {

    @DubboReference
    private IBbcTradeRpc bbcTradeRpc;

    @ApiOperation("去结算")
    @PostMapping("/userCenter/settlement")
    public ResponseData<BbcTradeSettlementVO.DetailVO> settlement(@Valid @RequestBody BbcTradeBuildDTO.cartIdsDTO dto) {
        dto.setTerminal(ActivityTerminalEnum.pc端);
        return bbcTradeRpc.settlementVO(dto);
    }

     // 这是退货的方法
//    @ApiOperation("计算运费")
//    @PostMapping("/userCenter/deliveryAmount")
//    public ResponseData<Void> deliveryAmount(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {
//
//        return bbbPcTradeRpc.deliveryAmount(dto);
//    }

    @ApiOperation("提交订单")
    @PostMapping("/userCenter/orderSubmit")
    public ResponseData<BbcTradeDTO.ListIdDTO> orderSubmit(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {
        dto.setTerminal(ActivityTerminalEnum.pc端);
        return bbcTradeRpc.orderSubmit(dto);
    }
//
//
//    @ApiOperation("支付")
//    @PostMapping("/userCenter/doPay")
//    public ResponseData<Void> doPay(@Valid @RequestBody BbcTradePayBuildDTO.ETO dto) {
//
//        return bbbPcTradeRpc.orderPay(dto);
//    }
//
//    @ApiOperation("支付回调")
//    @PostMapping("/doPayNotify")
//    public String doPayNotify(@Valid @RequestBody BbcTradeResultNotifyVO.notifyVO notifyVO) {
//
//        return bbbPcTradeRpc.isFinishedPay(notifyVO);
//    }
//
//    @ApiOperation("支付成功")
//    @PostMapping("/doPaySuccess")
//    public String doPaySuccess(@Valid @RequestBody String tradeCode) {
//
//        return bbbPcTradeRpc.paySuccess(tradeCode);
//    }
//
    @ApiOperation("订单列表")
    @PostMapping("/userCenter/orderList")
    public ResponseData<PageData<BbcTradeListVO.tradeVO>> orderList(@RequestBody BbcTradeQTO.TradeList qto) {
        return ResponseData.data(bbcTradeRpc.tradeListPageData(qto));
    }

//    @ApiOperation("近期订单")
//    @PostMapping("/userCenter/latelyTrade")
//    public ResponseData<List<BbbTradeListVO.tradeVO>> latelyTrade(@RequestBody BbbOrderDTO.StateDTO dto) {
//
//        return ResponseData.data(bbbPcTradeRpc.latelyTrade(dto));
//    }

    @ApiOperation("订单详情")
    @PostMapping("/userCenter/orderDetail")
    public ResponseData<BbcTradeListVO.tradeVO> orderDetail(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {
        return bbcTradeRpc.orderDetail(dto);
    }

//    @ApiOperation("修改凭证信息")
//    @GetMapping("/userCenter/offlineDetail")
//    public ResponseData<BbbTradeListVO.OfflinePayVO> offlineDetail(BbbOrderDTO.IdDTO dto ) {
//
//        return bbbPcTradeRpc.offlineDetail(dto);
//    }

    @ApiOperation("线下支付")
    @PostMapping("/userCenter/offlinePay")
    public ResponseData<Void> offlinePay(@Valid @RequestBody BbcTradeDTO.OfflinePayDTO dto) {
        bbcTradeRpc.offlinePay(dto);
        return ResponseData.success(MsgConst.OFFLINEPAY_SUCCESS);
    }
//
    @ApiOperation("订单确认收货")
    @PostMapping("/userCenter/orderConfirmReceipt")
    public ResponseData<Void> orderConfirmReceipt(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {
        return bbcTradeRpc.orderConfirmReceipt(dto);
    }
//
//    @ApiOperation("隐藏订单")
//    @PostMapping("/userCenter/orderHide")
//    public ResponseData<Void> orderHide(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {
//
//        return bbbPcTradeRpc.orderHide(dto);
//    }
//
    @ApiOperation("取消订单")
    @PostMapping("/userCenter/orderCancel")
    public ResponseData<Void> orderCancel(@Valid @RequestBody BbcTradeCancelDTO.CancelDTO dto) {
        return bbcTradeRpc.orderCancel(dto);
    }
//
//    @ApiOperation("订单状态数量")
//    @PostMapping("/userCenter/tradeStateCount")
//    public ResponseData<List<BbcTradeListVO.stateCountVO>> tradeStateCount(@RequestBody BbcTradeDTO.IdDTO dto) {
//
//        return ResponseData.data(bbbPcTradeRpc.tradeStateCount(dto));
//    }
//    @ApiOperation("最新订单")
//    @GetMapping("/newTrade")
//    public ResponseData<List<BbbTradeListVO.tradeVO>> newTrade() {
//
//        return bbbPcTradeRpc.newTrade();
//    }
    @ApiOperation("使用优惠卷列表")
    @PostMapping("/useCard")
    public ResponseData<List<BbcTradeListVO.UseCard>> useCard(@Valid @RequestBody BbcTradeDTO.UseCard dto) {
        return ResponseData.data(bbcTradeRpc.useCard(dto));
    }

}
