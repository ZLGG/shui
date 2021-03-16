package com.gs.lshly.facade.bbc.controller.common.trade;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCommonTradeResultNotifyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * 交易订单管理
 *
 * @author shinl
 * @date 2021/3/12 11:02
 */
@RestController
@RequestMapping("/bbc/common/trade")
@Api(tags = "交易订单管理")
public class BbcCommonTradeController {

    @ApiOperation("交易订单支付状态回调")
    @PostMapping(value = "/result/notify")
    public ResponseData<String> resultNotify(@RequestBody BbcCommonTradeResultNotifyVO.notifyVO notifyVO) {


        return ResponseData.data("ok");
    }
}
