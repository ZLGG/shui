package com.gs.lshly.facade.bbb.controller.h5.foundation;

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
@RequestMapping("/bbb/h5/siteService")
@Api(tags = "平台客服数据",description = " ")
public class BbbH5SiteCustomerServiceController {


    @DubboReference
    private ICommonSiteCustomerServiceRpc commonSiteCustomerServiceRpc;

    @ApiOperation("平台客服数据")
    @PostMapping("")
    public ResponseData<CommonSiteCustomerServiceVO.ServiceVO> siteService() {
        return ResponseData.data( commonSiteCustomerServiceRpc.getService(new BaseDTO()));
    }
}
