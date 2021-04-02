package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantRoleDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantRoleDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantRoleDictVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchMerchantRoleDictRpc;
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
@RequestMapping("/merchant/merchantRoleDict")
@Api(tags = "商家帐号角色字典管理",description = " ")
public class PCMerchantRoleDictController {

    @DubboReference
    private IPCMerchMerchantRoleDictRpc pCMerchMerchantRoleDictRpc;

    @ApiOperation("商家帐号角色字典列表")
    @GetMapping("")
    public ResponseData<PageData<MerchantRoleDictVO.ListVO>> list(MerchantRoleDictQTO.QTO qto) {
        return ResponseData.data(pCMerchMerchantRoleDictRpc.pageData(qto));
    }

    @ApiOperation("商家帐号角色字典详情")
    @GetMapping(value = "/{id}")
    public ResponseData<MerchantRoleDictVO.DetailVO> detail(@PathVariable String id) {

        return ResponseData.data(pCMerchMerchantRoleDictRpc.detail(new MerchantRoleDictDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家帐号角色字典")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody MerchantRoleDictDTO.ETO dto) {
        pCMerchMerchantRoleDictRpc.addMerchantRoleDict(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家帐号角色字典")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        MerchantRoleDictDTO.IdDTO dto = new MerchantRoleDictDTO.IdDTO(id);
        pCMerchMerchantRoleDictRpc.deleteMerchantRoleDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家帐号角色字典")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody MerchantRoleDictDTO.ETO eto) {
        eto.setId(id);
        pCMerchMerchantRoleDictRpc.editMerchantRoleDict(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


}
