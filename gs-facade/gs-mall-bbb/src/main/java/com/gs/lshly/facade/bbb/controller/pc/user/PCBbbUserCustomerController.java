package com.gs.lshly.facade.bbb.controller.pc.user;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.rpc.api.bbb.pc.user.IBbbUserRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  客服
* </p>
*
* @author xxfc
* @since 2020-10-22
*/
@RestController
@RequestMapping("/bbb/userCenter/customer")
@Api(tags = "客服-v1.1.0",description = " ")
public class PCBbbUserCustomerController {

    @DubboReference
    private IBbbUserRpc bbbUserRpc;

    @ApiOperation("获取客服授权信息-v1.1.0")
    @GetMapping("")
    public ResponseData<String> authorize(BaseDTO dto) {
        return ResponseData.data(bbbUserRpc.customerAuthorize(dto));
    }
}
