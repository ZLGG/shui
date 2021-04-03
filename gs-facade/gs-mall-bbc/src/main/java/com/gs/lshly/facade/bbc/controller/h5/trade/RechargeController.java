package com.gs.lshly.facade.bbc.controller.h5.trade;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bb.trade.dto.RechargeOrderDTO;
import com.gs.lshly.common.struct.bb.trade.qto.RechargeOrderQTO;
import com.gs.lshly.common.struct.bb.trade.vo.BbRechargeVO;
import com.gs.lshly.rpc.api.bb.trade.IBbRechargeOrderRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
* 话费充值
* </p>
*
* @author oy
* @since 2020-12-06
*/
@RestController
@RequestMapping("/bbc/recharge")
@Api(tags = "话费充值-模拟数据-v1.1.0")
public class RechargeController {

    @DubboReference
    private IBbRechargeOrderRpc rechargeOrderRpc;

    @ApiOperation("获取当前用户的话费余额信息-v1.1.0")
    @GetMapping("/phonebill")
    public ResponseData<BbRechargeVO.PhoneBillVO> phonebill(RechargeOrderQTO.QTO qto) {
        return ResponseData.data(rechargeOrderRpc.getPhoneBill(qto));
    }
    
    @ApiOperation("充值-v1.1.0")
    @PostMapping("/create")
    public ResponseData<Void> create(RechargeOrderDTO.ETO eto) {
    	rechargeOrderRpc.create(eto);
        return ResponseData.data(MsgConst.SUBMIT_SUCCESS);
    }
}
