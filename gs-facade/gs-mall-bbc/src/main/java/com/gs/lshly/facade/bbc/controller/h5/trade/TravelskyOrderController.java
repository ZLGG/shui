package com.gs.lshly.facade.bbc.controller.h5.trade;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
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

    @ApiOperation("订购电子票-v1.1.0")
    @GetMapping("/order/create")
    public ResponseData<String> createOrder() {
        return ResponseData.data(travelskyOrderRpc.createOrder());
    }
}
