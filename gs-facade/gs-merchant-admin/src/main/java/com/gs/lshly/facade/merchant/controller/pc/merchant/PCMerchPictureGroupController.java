package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPictureGroupDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.qto.PCMerchPictureGroupQTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchPictureGroupVO;
import com.gs.lshly.rpc.api.merchadmin.pc.merchant.IPCMerchPictureGroupRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-22
*/
@RestController
@RequestMapping("/merchadmin/pictureGroup")
@Api(tags = "商家店铺图片分组管理管理")
public class PCMerchPictureGroupController {

    @DubboReference
    private IPCMerchPictureGroupRpc pcMerchPictureGroupRpc;

    @ApiOperation("商家店铺图片分组管理列表")
    @GetMapping("")
    public ResponseData<List<PCMerchPictureGroupVO.ListVO>> list(PCMerchPictureGroupQTO.BelongIdQTO qto) {
        return ResponseData.data(pcMerchPictureGroupRpc.listGroup(qto));
    }


    @ApiOperation("新增商家店铺图片分组管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchPictureGroupDTO.ETO dto) {
            pcMerchPictureGroupRpc.addPictureGroup(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("编辑商家店铺图片分组管理")
    @PutMapping(value = "{id}")
    public ResponseData<Void> edit(@PathVariable String id, @RequestBody PCMerchPictureGroupDTO.ETO eto) {
        eto.setId(id);
        pcMerchPictureGroupRpc.editPictureGroup(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("删除商家店铺图片分组管理")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchPictureGroupDTO.IdDTO dto = new PCMerchPictureGroupDTO.IdDTO(id);
        pcMerchPictureGroupRpc.deletePictureGroup(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }



}
