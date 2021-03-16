package com.gs.lshly.facade.bbb.controller.pc.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeBuildDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCancelDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.BbbOrderQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeSettlementVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbPcTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    private IBbbPcTradeRpc bbbPcTradeRpc;
    @ApiOperation("去结算")
    @PostMapping("/userCenter/settlement")
    public ResponseData<BbbTradeSettlementVO.ListVO> settlement(@Valid @RequestBody BbbTradeBuildDTO.cartIdsDTO dto) {
        dto.setTerminal(ActivityTerminalEnum.pc端);
        return bbbPcTradeRpc.settlementVO(dto);
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
    public ResponseData<BbbOrderDTO.IdDTO> orderSubmit(@Valid @RequestBody BbbTradeBuildDTO.DTO dto) {
        dto.setTerminal(ActivityTerminalEnum.pc端);
        return bbbPcTradeRpc.orderSubmit(dto);
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
    public ResponseData<PageData<BbbTradeListVO.tradeVO>> orderList(@RequestBody BbbOrderQTO.TradeList qto) {

        return ResponseData.data(bbbPcTradeRpc.tradeListPageData(qto));
    }
    @ApiOperation("近期订单")
    @PostMapping("/userCenter/latelyTrade")
    public ResponseData<List<BbbTradeListVO.tradeVO>> latelyTrade(@RequestBody BbbOrderDTO.StateDTO dto) {

        return ResponseData.data(bbbPcTradeRpc.latelyTrade(dto));
    }

    @ApiOperation("订单详情")
    @PostMapping("/userCenter/orderDetail")
    public ResponseData<BbbTradeListVO.tradeVO> orderDetail(@Valid @RequestBody BbbOrderDTO.IdDTO dto) {

        return bbbPcTradeRpc.orderDetail(dto);
    }

    @ApiOperation("修改凭证信息")
    @GetMapping("/userCenter/offlineDetail")
    public ResponseData<BbbTradeListVO.OfflinePayVO> offlineDetail(BbbOrderDTO.IdDTO dto ) {

        return bbbPcTradeRpc.offlineDetail(dto);
    }
    @ApiOperation("线下支付")
    @PostMapping("/userCenter/offlinePay")
    public ResponseData<Void> offlinePay(@Valid @RequestBody BbbOrderDTO.OfflinePayDTO dto) {
        bbbPcTradeRpc.offlinePay(dto);
        return ResponseData.success(MsgConst.OFFLINEPAY_SUCCESS);
    }
//
    @ApiOperation("订单确认收货")
    @PostMapping("/userCenter/orderConfirmReceipt")
    public ResponseData<Void> orderConfirmReceipt(@Valid @RequestBody BbbOrderDTO.IdDTO dto) {

        return bbbPcTradeRpc.orderConfirmReceipt(dto);
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
    public ResponseData<Void> orderCancel(@Valid @RequestBody BbbTradeCancelDTO.CancelDTO dto) {

        return bbbPcTradeRpc.orderCancel(dto);
    }
//
//    @ApiOperation("订单状态数量")
//    @PostMapping("/userCenter/tradeStateCount")
//    public ResponseData<List<BbcTradeListVO.stateCountVO>> tradeStateCount(@RequestBody BbcTradeDTO.IdDTO dto) {
//
//        return ResponseData.data(bbbPcTradeRpc.tradeStateCount(dto));
//    }
    @ApiOperation("最新订单")
    @GetMapping("/newTrade")
    public ResponseData<List<BbbTradeListVO.tradeVO>> newTrade() {

        return bbbPcTradeRpc.newTrade();
    }
    @ApiOperation("使用优惠卷列表")
    @PostMapping("/useCard")
    public ResponseData<List<BbbTradeListVO.UseCard>> useCard(@Valid @RequestBody BbbOrderDTO.UseCard dto) {

        return ResponseData.data(bbbPcTradeRpc.useCard(dto));
    }

}
