package com.gs.lshly.facade.bbc.controller.fy;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.fy.fundation.vo.FyCustomerServiceVO;
import com.gs.lshly.rpc.api.fy.IFyCustomerServiceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fy/customerService")
@Api(tags = "平台在线客服")
public class FyCustomerServiceController {

    @DubboReference
    private IFyCustomerServiceRpc fyCustomerServiceRpc;

    @ApiOperation("平台客服数据")
    @PostMapping("")
    public ResponseData<FyCustomerServiceVO.ServiceVO> siteService() {
        return ResponseData.data( fyCustomerServiceRpc.getService(new BaseDTO()));
    }

}
