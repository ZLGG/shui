package com.gs.lshly.facade.bbc.controller.fy;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.fy.wallet.FyBaseRespDTO;
import com.gs.lshly.common.struct.fy.wallet.dto.*;
import com.gs.lshly.rpc.api.fy.IFyWalletRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/28 下午4:37
 */
@Api(tags = "富友支付管理")
@RestController
@RequestMapping("/fy/wallet")
public class FyWalletController {

    @DubboReference
    private IFyWalletRpc fyWalletRpc;

    @ApiOperation("II、III类账户消费：0000 成功，无需回填;0001 需要回填短信;其它失败;")
    @PostMapping("/accountConsume")
    public ResponseData<FyBaseRespDTO<AccountConsumeRespDTO>> accountConsume(@Valid @RequestBody AccountConsumeParamsDTO.ETO eto) {
        return ResponseData.data(fyWalletRpc.accountConsume(eto));
    }

    @ApiOperation("查询交易")
    @PostMapping("/queryAccountTrade")
    public ResponseData<FyBaseRespDTO<QueryAccountTradeRespDTO>> queryAccountTrade(@Valid @RequestBody QueryAccountTradeParamsDTO dto) {
        return ResponseData.data(fyWalletRpc.queryAccountTrade(dto));
    }

    @ApiOperation("查询余额")
    @PostMapping("/queryAccountBalance")
    public ResponseData<FyBaseRespDTO<QueryAccountBalanceRespDTO>> queryAccountBalance(@RequestBody BaseDTO dto) {
        return ResponseData.data(fyWalletRpc.queryAccountBalance(dto));
    }

    @ApiOperation("撤销消费")
    @PostMapping("/cancelAccountConsume")
    public ResponseData<FyBaseRespDTO<CancelAccountConsumeRespDTO>> cancelAccountConsume(@Valid @RequestBody CancelAccountConsumeParamsDTO.ETO eto) {
        return ResponseData.data(fyWalletRpc.cancelAccountConsume(eto));
    }

    @ApiOperation("消费退货")
    @PostMapping("/accountConsumeReturn")
    public ResponseData<FyBaseRespDTO<AccountConsumeReturnRespDTO>> accountConsumeReturn(@Valid @RequestBody AccountConsumeReturnParamsDTO.ETO eto) {
        return ResponseData.data(fyWalletRpc.accountConsumeReturn(eto));
    }

    @ApiOperation("查询消费/撤销/退货结果")
    @PostMapping("/queryConsumeOrCancelOrReturnResult")
    public ResponseData<FyBaseRespDTO<QueryConsumeOrCancelOrReturnResultRespDTO>> queryConsumeOrCancelOrReturnResult(@Valid @RequestBody QueryConsumeOrCancelOrReturnResultParamsDTO dto) {
        return ResponseData.data(fyWalletRpc.queryConsumeOrCancelOrReturnResult(dto));
    }

    @ApiOperation("验证短信验证码")
    @PostMapping("/replyMsgCode")
    public ResponseData<FyBaseRespDTO<ReplyMsgCodeRespDTO>> replyMsgCode(@Valid @RequestBody ReplyMsgCodeParamsDTO dto) {
        return ResponseData.data(fyWalletRpc.replyMsgCode(dto));
    }


}
