package com.gs.lshly.facade.bbb.controller.h5.trade;
import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeCancelDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradePayBuildDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.qto.BbbH5TradeQTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeResultNotifyVO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeSettlementVO;
import com.gs.lshly.rpc.api.bbb.h5.trade.IBbbH5TradeRpc;

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
@RequestMapping("/bbb/h5/userCenter/trade")
@Api(tags = "交易订单管理")
public class BbbH5TradeController {

    @DubboReference
    private IBbbH5TradeRpc bbcTradeRpc;

    @ApiOperation("去结算")
    @PostMapping("/userCenter/settlement")
    public ResponseData<BbbH5TradeSettlementVO.ListVO> settlement(@Valid @RequestBody BbbH5TradeBuildDTO.cartIdsDTO dto) {
        dto.setTerminal(ActivityTerminalEnum.wap端);
        return bbcTradeRpc.settlementVO(dto);
    }

    @ApiOperation("计算运费")
    @PostMapping("/userCenter/deliveryAmount")
    public ResponseData<Void> deliveryAmount(@Valid @RequestBody BbbH5TradeBuildDTO.DTO dto) {
        dto.setTerminal(ActivityTerminalEnum.wap端);
        return bbcTradeRpc.deliveryAmount(dto);
    }

    @ApiOperation("提交订单")
    @PostMapping("/userCenter/orderSubmit")
    public ResponseData<BbbH5TradeDTO.IdDTO> orderSubmit(@Valid @RequestBody BbbH5TradeBuildDTO.DTO dto) {
        dto.setTerminal(ActivityTerminalEnum.wap端);
        return bbcTradeRpc.orderSubmit(dto);
    }


    @ApiOperation("支付")
    @PostMapping("/userCenter/doPay")
    public ResponseData<Void> doPay(@Valid @RequestBody BbbH5TradePayBuildDTO.ETO dto) {

        return bbcTradeRpc.orderPay(dto);
    }

    @ApiOperation("支付回调")
    @PostMapping("/doPayNotify")
    public String doPayNotify(@Valid @RequestBody BbbH5TradeResultNotifyVO.notifyVO notifyVO) {

        return bbcTradeRpc.payNotify(notifyVO);
    }

    @ApiOperation("支付成功")
    @PostMapping("/doPaySuccess")
    public String doPaySuccess(@Valid @RequestBody String tradeCode) {

        return bbcTradeRpc.paySuccess(tradeCode);
    }

    @ApiOperation("订单列表")
    @PostMapping("/userCenter/orderList")
    public ResponseData<PageData<BbbH5TradeListVO.tradeVO>> orderList(@RequestBody BbbH5TradeQTO.TradeList qto) {

        return ResponseData.data(bbcTradeRpc.tradeListPageData(qto));
    }

    @ApiOperation("订单详情")
    @PostMapping("/userCenter/orderDetail")
    public ResponseData<BbbH5TradeListVO.tradeVO> orderDetail(@Valid @RequestBody BbbH5TradeDTO.IdDTO dto) {

        return bbcTradeRpc.orderDetail(dto);
    }

    @ApiOperation("订单确认收货")
    @PostMapping("/userCenter/orderConfirmReceipt")
    public ResponseData<Void> orderConfirmReceipt(@Valid @RequestBody BbbH5TradeDTO.IdDTO dto) {

        return bbcTradeRpc.orderConfirmReceipt(dto);
    }

    @ApiOperation("隐藏订单")
    @PostMapping("/userCenter/orderHide")
    public ResponseData<Void> orderHide(@Valid @RequestBody BbbH5TradeDTO.IdDTO dto) {

        return bbcTradeRpc.orderHide(dto);
    }

    @ApiOperation("取消订单")
    @PostMapping("/userCenter/orderCancel")
    public ResponseData<Void> orderCancel(@Valid @RequestBody BbbH5TradeCancelDTO.CancelDTO dto) {

        return bbcTradeRpc.orderCancel(dto);
    }

    @ApiOperation("订单状态数量")
    @PostMapping("/userCenter/tradeStateCount")
    public ResponseData<List<BbbH5TradeListVO.stateCountVO>> tradeStateCount(@RequestBody BbbH5TradeDTO.IdDTO dto) {

        return ResponseData.data(bbcTradeRpc.tradeStateCount(dto));
    }

    @ApiOperation("使用优惠卷列表")
    @PostMapping("/useCard")
    public ResponseData<List<BbbH5TradeListVO.UseCard>> useCard(@Valid @RequestBody BbbH5TradeDTO.UseCard dto) {

        return ResponseData.data(bbcTradeRpc.useCard(dto));
    }
    @ApiOperation("删除订单")
    @PostMapping("/deleteTrade")
    public ResponseData<Void> deleteTrade(@Valid @RequestBody BbbH5TradeDTO.IdDTO dto) {
        bbcTradeRpc.deleteTrade(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

}
