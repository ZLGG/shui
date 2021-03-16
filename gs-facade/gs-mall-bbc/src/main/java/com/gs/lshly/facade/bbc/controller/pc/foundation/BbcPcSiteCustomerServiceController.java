package com.gs.lshly.facade.bbc.controller.pc.foundation;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteCustomerServiceVO;
import com.gs.lshly.rpc.api.common.ICommonSiteCustomerServiceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bbc/pc/siteService")
@Api(tags = "2C PC平台客服数据")
public class BbcPcSiteCustomerServiceController {


    @DubboReference
    private ICommonSiteCustomerServiceRpc commonSiteCustomerServiceRpc;

    @ApiOperation("平台客服数据")
    @PostMapping("")
    public ResponseData<CommonSiteCustomerServiceVO.ServiceVO> siteService() {
        return ResponseData.data( commonSiteCustomerServiceRpc.getService(new BaseDTO()));
    }
}
