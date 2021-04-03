package com.gs.lshly.facade.bbc.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.ActivityTerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbOrderDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.dto.BbbTradeCancelDTO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.BbbTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradeDTO;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTradePayBuildDTO;
import com.gs.lshly.common.struct.bbc.trade.qto.BbcTradeQTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeResultNotifyVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeSettlementVO;
import com.gs.lshly.common.utils.JsonUtils;
import com.gs.lshly.common.utils.QRCodeUtil;
import com.gs.lshly.rpc.api.bbb.pc.trade.IBbbPcTradeRpc;
import com.gs.lshly.rpc.api.bbc.trade.IBbcTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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
@RequestMapping("/bbc/pc")
@Api(tags = "2C PC交易订单管理")
@Slf4j
public class BbcPcTradeController {

    @DubboReference
    private IBbbPcTradeRpc bbbPcTradeRpc;

    @DubboReference
    private IBbcTradeRpc bbcTradeRpc;

    @ApiOperation("去结算")
    @PostMapping("/userCenter/settlement")
    public ResponseData<BbcTradeSettlementVO.ListVO> settlement(@Valid @RequestBody BbcTradeBuildDTO.cartIdsDTO dto) {
        dto.setTerminal(ActivityTerminalEnum.pc端);
        return bbcTradeRpc.settlementVO(dto);
    }
    @ApiOperation("提交订单")
    @PostMapping("/userCenter/orderSubmit")
    public ResponseData<BbcTradeDTO.IdDTO> orderSubmit(@Valid @RequestBody BbcTradeBuildDTO.DTO dto) {
        log.info("----------------------提交订单-----------------");
        dto.setTerminal(ActivityTerminalEnum.pc端);
        return bbcTradeRpc.orderSubmit(dto);
    }
    @ApiOperation("订单列表")
    @PostMapping("/userCenter/orderList")
    public ResponseData<PageData<BbcTradeListVO.tradeVO>> orderList(@RequestBody BbcTradeQTO.TradeList qto) {
        return ResponseData.data(bbcTradeRpc.tradeListPageData(qto));
    }

    @ApiOperation("近期订单")
    @PostMapping("/userCenter/latelyTrade")
    public ResponseData<List<BbbTradeListVO.tradeVO>> latelyTrade(@RequestBody BbbOrderDTO.StateDTO dto) {
        return ResponseData.data(bbbPcTradeRpc.latelyTrade(dto));
    }

    @ApiOperation("订单详情")
    @PostMapping("/userCenter/orderDetail")
    public ResponseData<BbcTradeListVO.tradeVO> orderDetail(@Valid @RequestBody BbcTradeDTO.IdDTO dto) {
        return bbcTradeRpc.orderDetail(dto);
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

    @ApiOperation("订单确认收货")
    @PostMapping("/userCenter/orderConfirmReceipt")
    public ResponseData<Void> orderConfirmReceipt(@Valid @RequestBody BbbOrderDTO.IdDTO dto) {
        return bbbPcTradeRpc.orderConfirmReceipt(dto);
    }

    @ApiOperation("取消订单")
    @PostMapping("/userCenter/orderCancel")
    public ResponseData<Void> orderCancel(@Valid @RequestBody BbbTradeCancelDTO.CancelDTO dto) {
        return bbbPcTradeRpc.orderCancel(dto);
    }

    @ApiOperation("最新订单")
    @GetMapping("/userCenter/newTrade")
    public ResponseData<List<BbbTradeListVO.tradeVO>> newTrade() {
        return bbbPcTradeRpc.newTrade();
    }

    @ApiOperation("支付(返回支付二维码请求地址)")
    @PostMapping("/userCenter/doPay")
    public ResponseData<Void> doPay(@Valid @RequestBody BbcTradePayBuildDTO.ETO dto) {
        return bbcTradeRpc.orderPay(dto);

//        ResponseData responseData= bbbPcTradeRpc.orderPay(dto);
//        if(responseData.isSuccess()){
//            if(!ObjectUtils.isEmpty(responseData.getData())){
//                String qrCodeContent=(String) responseData.getData();
//                try {
//                    String tmpFileName = StrUtil.uuid();
//                    QRCodeUtil.genAndSaveFile(qrCodeContent, 300, 300, tmpFileName);
//                    return ResponseData.data(tmpFileName);
//                }catch(Exception e){
//                    e.printStackTrace();
//                    return ResponseData.fail("二维码生成失败");
//                }
//            }
//        }
//        return responseData;
    }

    @ApiOperation("支付回调")
    @PostMapping("/doPayNotify")
    public String doPayNotify(BbcTradeResultNotifyVO.notifyVO notifyVO, HttpServletRequest request) {
        log.info("pc回调:"+ JsonUtils.toJson(notifyVO));
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            log.info(name + ":" + request.getParameter(name));
        }
        return bbbPcTradeRpc.payNotify(notifyVO);
    }

    @ApiOperation("支付二维码")
    @GetMapping("/userCenter/payImg/{fileName}")
    public void payImg(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
        try {
            QRCodeUtil.writeToResponse(response, fileName);
        }catch (IOException e){
            log.error(e.getMessage(), e);
            PrintWriter toClient=response.getWriter();
            response.setContentType("text/html;charset=gb2312");
            toClient.write("无法打开图片");
            toClient.close();
        }
    }

    @ApiOperation("支付是否完成")
    @PostMapping("/userCenter/isFinishedPay/{tradeId}")
    public ResponseData<Void> isFinishedPay(@PathVariable("tradeId") String tradeId,String userCard) {
        return ResponseData.data(bbbPcTradeRpc.isFinishedPay(tradeId,userCard));
    }
}
