package com.gs.lshly.facade.merchant.controller.pc.merchant;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.dto.PCMerchPictureGroupDTO;
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
    public ResponseData<List<PCMerchPictureGroupVO.ListVO>> list(PCMerchPictureGroupDTO.ParentIdDTO dto) {
        return ResponseData.data(pcMerchPictureGroupRpc.listGroup(dto));
    }


    @ApiOperation("批量保存商家店铺图片分组")
    @PostMapping("")
    public ResponseData<Void> save(@Valid @RequestBody PCMerchPictureGroupDTO.SaveETO eto) {
            pcMerchPictureGroupRpc.addPictureGroup(eto,new BaseDTO());
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("批量删除商家店铺图片分组")
    @PostMapping(value = "/deleteBatches")
    public ResponseData<Void> delete(@RequestBody PCMerchPictureGroupDTO.IdListDTO dto) {
        pcMerchPictureGroupRpc.deletePictureGroup(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }



}
