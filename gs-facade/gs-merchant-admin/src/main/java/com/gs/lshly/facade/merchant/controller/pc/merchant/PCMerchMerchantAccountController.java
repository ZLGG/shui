package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchMerchantAccountDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchMerchantAccountQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantAccountVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantAccountRpc;
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
* @since 2020-10-23
*/
@RestController
@RequestMapping("/merchadmin/merchantAccount")
@Api(tags = "商家帐号管理",description = " ")
@Module(code = "accountManagement", parent = "accountNumber", name = "账号管理", index = 1)
public class PCMerchMerchantAccountController {

    @DubboReference
    private IPCMerchMerchantAccountRpc pcMerchMerchantAccountRpc;

    @ApiOperation("商家帐号列表-v1.1.0")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<PCMerchMerchantAccountVO.ListVO>> list(PCMerchMerchantAccountQTO.QTO qto) {
        return ResponseData.data(pcMerchMerchantAccountRpc.pageData(qto));
    }

    @ApiOperation("商家帐号详情-v1.1.0")
    @GetMapping(value = "/{id}")
    @Func(code="view", name="查")
    public ResponseData<PCMerchMerchantAccountVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchMerchantAccountRpc.detailMerchantAccount(new PCMerchMerchantAccountDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家帐号")
    @PostMapping("")
    @Func(code="add", name="增")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchMerchantAccountDTO.AddDTO dto) {
            pcMerchMerchantAccountRpc.addMerchantAccount(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家帐号")
    @DeleteMapping(value = "/{id}")
    @Func(code="delete", name="删")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchMerchantAccountDTO.IdDTO dto = new PCMerchMerchantAccountDTO.IdDTO(id);
        pcMerchMerchantAccountRpc.deleteMerchantAccount(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("编辑商家帐号v1.1.0")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchMerchantAccountDTO.ETO eto) {
        eto.setId(id);
        pcMerchMerchantAccountRpc.editMerchantAccount(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("修改商家帐号密码")
    @PutMapping("/updatePassworld")
    @Func(code="edit", name="改")
    public ResponseData<Void> updatePassworld(@Valid @RequestBody PCMerchMerchantAccountDTO.PassworldETO dto) {
        pcMerchMerchantAccountRpc.updatePassworld(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("检查店铺(商家帐号登录的时候检查店铺是不是有开通)")
    @GetMapping("/checkShop")
    @Func(code="view", name="查")
    public ResponseData<PCMerchMerchantAccountVO.CheckShopVO> checkShop() {
        return ResponseData.data(pcMerchMerchantAccountRpc.checkShop(new BaseDTO()));
    }

}
