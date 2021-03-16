package com.gs.lshly.facade.platform.controller.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.MerchantPermissionDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.MerchantPermissionDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.MerchantPermissionDictVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IMerchantPermissionDictRpc;
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
@RequestMapping("/merchant/merchantPermissionDict")
@Api(tags = "商家帐号权限字典管理",description = " ")
public class MerchantPermissionDictController {

    @DubboReference
    private IMerchantPermissionDictRpc merchantPermissionDictRpc;

    @ApiOperation("商家帐号权限字典列表")
    @GetMapping("")
    public ResponseData<PageData<MerchantPermissionDictVO.ListVO>> list(MerchantPermissionDictQTO.QTO qto) {
        return ResponseData.data(merchantPermissionDictRpc.pageData(qto));
    }

    @ApiOperation("商家帐号权限字典详情")
    @GetMapping(value = "/{id}")
    public ResponseData<MerchantPermissionDictVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(merchantPermissionDictRpc.detailMerchantPermissionDict(new MerchantPermissionDictDTO.IdDTO(id)));
    }

    @ApiOperation("新增商家帐号权限字典")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody MerchantPermissionDictDTO.ETO dto) {
        merchantPermissionDictRpc.addMerchantPermissionDict(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商家帐号权限字典")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        MerchantPermissionDictDTO.IdDTO dto = new MerchantPermissionDictDTO.IdDTO(id);
        merchantPermissionDictRpc.deleteMerchantPermissionDict(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商家帐号权限字典")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody MerchantPermissionDictDTO.ETO eto) {
        eto.setId(id);
        merchantPermissionDictRpc.editMerchantPermissionDict(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
