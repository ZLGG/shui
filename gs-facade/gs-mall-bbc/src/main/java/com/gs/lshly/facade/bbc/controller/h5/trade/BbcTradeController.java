package com.gs.lshly.facade.bbc.controller.h5.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.qto.PCBbbMarketMerchantCardUsersQTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketMerchantCardUsersVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeCancelDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping("/bbc")
@Api(tags = "交易订单管理")
public class BbcTradeController {

    @DubboReference
    private IBbcTradeRpc bbcTradeRpc;

    @ApiOperation("去结算")
    @PostMapping("/userCenter/settlement")
    public ResponseData<BbcTradeSettlementVO.ListVO> settlement(@Valid @RequestBody BbcTradeBuildDTO.cartIdsDTO dto) {
        dto.setTerminal(ActivityTerminalEnum.wap端);
        return bbcTradeRpc.settlementVO(dto);
    }

    @ApiOperation("计算运费")
    @PostMapping("/userCenter/deliveryAmount")
    public ResponseData<Void> deliveryAmount(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {
        dto.setTerminal(ActivityTerminalEnum.wap端);
        return bbcTradeRpc.deliveryAmount(dto);
    }

    @ApiOperation("提交订单")
    @PostMapping("/userCenter/orderSubmit")
    public ResponseData<BbcTradeDTO.IdDTO> orderSubmit(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {
        dto.setTerminal(ActivityTerminalEnum.wap端);
        return bbcTradeRpc.orderSubmit(dto);
    }


    @ApiOperation("支付")
    @PostMapping("/userCenter/doPay")
    public ResponseData<Void> doPay(@Valid @RequestBody BbcTradePayBuildDTO.ETO dto) {

        return bbcTradeRpc.orderPay(dto);
    }

    @ApiOperation("支付回调")
    @PostMapping("/doPayNotify")
    public String doPayNotify(@Valid @RequestBody BbcTradeResultNotifyVO.notifyVO notifyVO) {

        return bbcTradeRpc.payNotify(notifyVO);
    }

    @ApiOperation("支付成功")
    @PostMapping("/doPaySuccess")
    public String doPaySuccess(@Valid @RequestBody String tradeCode) {

        return bbcTradeRpc.paySuccess(tradeCode);
    }

    @ApiOperation("订单列表")
    @PostMapping("/userCenter/orderList")
    public ResponseData<PageData<BbcTradeListVO.tradeVO>> orderList(@RequestBody BbcTradeQTO.TradeList qto) {

        return ResponseData.data(bbcTradeRpc.tradeListPageData(qto));
    }

    @ApiOperation("订单详情")
    @PostMapping("/userCenter/orderDetail")
    public ResponseData<BbcTradeListVO.tradeVO> orderDetail(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {

        return bbcTradeRpc.orderDetail(dto);
    }

    @ApiOperation("订单确认收货")
    @PostMapping("/userCenter/orderConfirmReceipt")
    public ResponseData<Void> orderConfirmReceipt(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {

        return bbcTradeRpc.orderConfirmReceipt(dto);
    }

    @ApiOperation("隐藏订单")
    @PostMapping("/userCenter/orderHide")
    public ResponseData<Void> orderHide(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {

        return bbcTradeRpc.orderHide(dto);
    }

    @ApiOperation("取消订单")
    @PostMapping("/userCenter/orderCancel")
    public ResponseData<Void> orderCancel(@Valid @RequestBody BbcTradeCancelDTO.CancelDTO dto) {

        System.out.println(dto);
        return bbcTradeRpc.orderCancel(dto);
    }

    @ApiOperation("订单状态数量")
    @PostMapping("/userCenter/tradeStateCount")
    public ResponseData<List<BbcTradeListVO.stateCountVO>> tradeStateCount(@RequestBody BbcTradeDTO.IdDTO dto) {

        return ResponseData.data(bbcTradeRpc.tradeStateCount(dto));
    }

    @ApiOperation("我的优惠卷列表（资产管理）")
    @GetMapping("myUserCard")
    public ResponseData<PageData<BbcTradeListVO.PageData>> myUserCard(BbcTradeQTO.UserCardQTO qto) {
        return ResponseData.data(bbcTradeRpc.myUserCard(qto));
    }
    @ApiOperation("使用优惠卷列表")
    @PostMapping("/useCard")
    public ResponseData<List<BbcTradeListVO.UseCard>> useCard(@Valid @RequestBody BbcTradeDTO.UseCard dto) {

        return ResponseData.data(bbcTradeRpc.useCard(dto));
    }
    @ApiOperation("线下支付")
    @PostMapping("/userCenter/offlinePay")
    public ResponseData<Void> offlinePay(@Valid @RequestBody BbcTradeDTO.OfflinePayDTO dto) {
         bbcTradeRpc.offlinePay(dto);
        return ResponseData.success(MsgConst.OFFLINEPAY_SUCCESS);
    }

}
