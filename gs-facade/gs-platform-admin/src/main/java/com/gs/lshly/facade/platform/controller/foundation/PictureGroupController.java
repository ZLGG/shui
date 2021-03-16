package com.gs.lshly.facade.platform.controller.foundation;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.foundation.dto.PictureGroupDTO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.PictureGroupQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.PictureGroupVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IPictureGroupRpc;
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
* @since 2020-10-06
*/
@RestController
@RequestMapping("/platadmin/pictureGroup")
@Api(tags = "文件分组管理管理")
public class PictureGroupController {

    @DubboReference
    private IPictureGroupRpc pictureGroupRpc;

    @ApiOperation("文件分组管理列表")
    @GetMapping("")
    public ResponseData<List<PictureGroupVO.ListVO>> list(PictureGroupQTO.QTO qto) {
        return ResponseData.data(pictureGroupRpc.pageData(qto));
    }

    @ApiOperation("文件分组管理详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PictureGroupVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pictureGroupRpc.detailPictureGroup(new PictureGroupDTO.IdDTO(id)));
    }

    @ApiOperation("新增文件分组管理")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PictureGroupDTO.ETO dto) {
        pictureGroupRpc.addPictureGroup(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除文件分组管理")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PictureGroupDTO.IdDTO dto = new PictureGroupDTO.IdDTO(id);
        pictureGroupRpc.deletePictureGroup(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("修改文件分组管理")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PictureGroupDTO.ETO eto) {
        eto.setId(id);
        pictureGroupRpc.editPictureGroup(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
