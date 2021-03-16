package com.gs.lshly.facade.platform.controller.foundation;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SiteDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.foundation.ISiteRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author hyy
* @since 2020-11-11
*/
@RestController
@RequestMapping("/platadmin/site")
@Api(tags = "商家登录配置管理",description = " ")
@Module(code = "configBussinessLogin", parent = "site", name = "商家登陆配置", index = 4)
public class SiteController {

    @DubboReference
    private ISiteRpc SiteRpc;

    @ApiOperation("商家登录配置列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<SiteVO.ListVO>> list(SiteQTO.QTO qto) {
        return ResponseData.data(SiteRpc.pageData(qto));
    }

    @ApiOperation("修改商家登录配置")
    @PutMapping(value = "/update")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@Valid @RequestBody SiteDTO.ETO eto) {
        SiteRpc.editSite(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }
}
