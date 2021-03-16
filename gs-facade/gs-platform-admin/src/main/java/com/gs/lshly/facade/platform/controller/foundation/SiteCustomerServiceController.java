package com.gs.lshly.facade.platform.controller.foundation;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteCustomerServiceDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteCustomerServiceQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteCustomerServiceVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteCustomerServiceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/platform/siteCustomerService/bbc")
@Api(tags = "平台在线客服")
@Module(code = "onlineService", parent = "site", name = "平台在线客服", index = 5)
public class SiteCustomerServiceController {
    @DubboReference
    private ISiteCustomerServiceRpc siteCustomerServiceRpc;

    @ApiOperation("平台在线客服展示")
    @GetMapping("/list")
    @Func(code="view", name="查")
    public ResponseData<List<SiteCustomerServiceVO.ListVO>>  list(SiteCustomerServiceQTO.QTO qto){
        return ResponseData.data(siteCustomerServiceRpc.list(qto));
    }

    @ApiOperation("新增或者修改平台在线客服")
    @PostMapping("")
    @Func(code="edit", name="改")
    public ResponseData<Void> add(@Valid @RequestBody SiteCustomerServiceDTO.ETO dto) {
        siteCustomerServiceRpc.addSiteCustomerServic(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("平台电话客服展示")
    @GetMapping("/listPhone")
    @Func(code="view", name="查")
    public ResponseData<List<SiteCustomerServiceVO.PhoneVO>>  listPhone(SiteCustomerServiceQTO.QTO qto){
        return ResponseData.data(siteCustomerServiceRpc.listPhone(qto));
    }

    @ApiOperation("新增或者修改平台电话客服")
    @PostMapping("/addPhone")
    @Func(code="edit", name="改")
    public ResponseData<Void> addPhone(@Valid @RequestBody SiteCustomerServiceDTO.ETOPhone dto) {
        siteCustomerServiceRpc.addSiteCustomerServicPhone(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }
}
