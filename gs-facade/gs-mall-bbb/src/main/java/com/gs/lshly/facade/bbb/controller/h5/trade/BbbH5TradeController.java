package com.gs.lshly.facade.bbb.controller.h5.trade;
import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.trade.dto.BbbH5TradeDTO;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
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

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-10-28
*/
@RestController
//@RequestMapping("/bbb/h5/userCenter/trade")
@RequestMapping("/bbb/h5")
@Api(tags = "交易订单管理")
public class BbbH5TradeController {

    @DubboReference
    private IBbcTradeRpc bbcTradeRpc;

    @ApiOperation("去结算")
    @PostMapping("/userCenter/settlement")
    public ResponseData<BbcTradeSettlementVO.DetailVO> settlement(@Valid @RequestBody BbcTradeBuildDTO.cartIdsDTO dto) {
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
    public ResponseData<BbcTradeDTO.ListIdDTO> orderSubmit(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {
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
    public ResponseData<PageData<BbbH5TradeListVO.tradeVO>> orderList(@RequestBody BbcTradeQTO.TradeList qto) {
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
        return bbcTradeRpc.orderCancel(dto);
    }

    @ApiOperation("订单状态数量")
    @PostMapping("/userCenter/tradeStateCount")
    public ResponseData<List<BbcTradeListVO.stateCountVO>> tradeStateCount(@RequestBody BbcTradeDTO.IdDTO dto) {

        return ResponseData.data(bbcTradeRpc.tradeStateCount(dto));
    }

    @ApiOperation("使用优惠卷列表")
    @PostMapping("/useCard")
    public ResponseData<List<BbcTradeListVO.UseCard>> useCard(@Valid @RequestBody BbcTradeDTO.UseCard dto) {
        return ResponseData.data(bbcTradeRpc.useCard(dto));
    }

    /**
     * 
     * @param dto
     * @return

    @ApiOperation("删除订单")
    @PostMapping("/deleteTrade")
    public ResponseData<Void> deleteTrade(@Valid @RequestBody BbbH5TradeDTO.IdDTO dto) {
        bbcTradeRpc.deleteTrade(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("线下支付")
    @PostMapping("/userCenter/offlinePay")
    public ResponseData<Void> offlinePay(@Valid @RequestBody BbbH5TradeDTO.OfflinePayDTO dto) {
        bbcTradeRpc.offlinePay(dto);
        return ResponseData.success(MsgConst.OFFLINEPAY_SUCCESS);
    }

    @ApiOperation("修改凭证信息")
    @GetMapping("/userCenter/offlineDetail")
    public ResponseData<BbbH5TradeListVO.OfflinePayVO> offlineDetail(BbbH5TradeDTO.IdDTO dto ) {

        return bbcTradeRpc.offlineDetail(dto);
    }
     */

}
