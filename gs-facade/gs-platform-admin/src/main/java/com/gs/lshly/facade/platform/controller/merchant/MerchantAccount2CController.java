package com.gs.lshly.facade.platform.controller.merchant;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantAccountDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantAccountQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantAccountVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantAccountRpc;
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
* @author xxfc
* @since 2020-10-08
*/
@RestController
@RequestMapping("/merchant/merchantAccount2c")
@Api(tags = "商家帐号管理(2C)",description = " ")
@Module(code = "listBusinessAccount2C", parent = "bussiness", name = "2C商家账号列表", index = 4)
public class MerchantAccount2CController {

    @DubboReference
    private IMerchantAccountRpc merchantAccountRpc;

    @ApiOperation("2C商家帐号分页列表")
    @GetMapping("/pageList")
    @Func(code="view", name="查")
    public ResponseData<PageData<MerchantAccountVO.ListVO>> pageList2C(MerchantAccountQTO.QTO qto) {
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(merchantAccountRpc.pageData(qto));
    }

    @ApiOperation("2C添加自营帐号")
    @PostMapping("/add")
    @Func(code="add", name="增")
    public ResponseData<Void> addMerchantAccountForPlatForm2C(@Valid @RequestBody MerchantAccountDTO.PlatformAccountETO eto) {
        merchantAccountRpc.addMerchantAccountForPlatForm(eto);
        return  ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("2C修改帐号密码")
    @PutMapping("/editorPwd/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> editMerchantAccountPwd2C(@PathVariable String id,@Valid @RequestBody MerchantAccountDTO.ModifyPwdETO eto) {
        eto.setId(id);
        merchantAccountRpc.editMerchantAccountPwd(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
