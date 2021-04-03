package com.gs.lshly.facade.bbc.controller.h5.trade;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.trade.dto.BbcTravelskyDTO;
import com.gs.lshly.rpc.api.travelsky.ITravelskyOrderRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-12-06
*/
@RestController
@RequestMapping("/bbc/travelsky")
@Api(tags = "信天游-v1.1.0")
public class TravelskyOrderController {

    @DubboReference
    private ITravelskyOrderRpc travelskyOrderRpc;

    @ApiOperation("订购电子票test-v1.1.0")
    @PostMapping("/order/create")
    public ResponseData<Void> createOrder(@Valid @RequestBody BbcTravelskyDTO.ETO eto) {
    	travelskyOrderRpc.createOrder(eto);
        return ResponseData.data(MsgConst.SUBMIT_SUCCESS);
    }
}
