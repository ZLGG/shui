package com.gs.lshly.facade.platform.controller.stock;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.common.CommonRegionVO;
import com.gs.lshly.common.struct.common.dto.CommonRegionDTO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.common.ICommonRegionRpc;
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
* @author zzg
* @since 2020-10-24
*/
@RestController
@RequestMapping("/platadmin/region")
@Api(tags = "国标准地区库管理")
@Module(code = "areaManagement",parent = "logistics",name = "地区管理" ,index = 3)
public class StockRegionController {

    @DubboReference
    private ICommonRegionRpc commonRegionRpc;

    @ApiOperation("国标准地区库列表(到县)")
    @GetMapping("/toCounty")
    @Func(code = "view" ,name = "查看")
    public ResponseData<List<CommonRegionVO.ProvinceVO>> listToCounty() {
        return ResponseData.data(commonRegionRpc.listToCounty());
    }

    @ApiOperation("国标准地区库(到市)")
    @GetMapping("/toCity")
    @Func(code = "view" ,name = "查看")
    public ResponseData<List<CommonRegionVO.ProvinceShortVO>> listToCity() {
        return ResponseData.data(commonRegionRpc.listToCity());
    }

    @ApiOperation("添加地区")
    @PostMapping("/addRegion")
    @Func(code = "add" ,name = "新增")
    public ResponseData<Void> addRegion(@Valid @RequestBody CommonRegionDTO.ETO dto) {

        commonRegionRpc.addRegion(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("修改地区")
    @PutMapping(value = "/{id}")
    @Func(code = "edit" ,name = "修改")
    public ResponseData<Void> editRegion(@PathVariable String id, @Valid @RequestBody CommonRegionDTO.EditRegion eto) {
        eto.setId(id);
        commonRegionRpc.editRegion(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


    @ApiOperation("删除地区")
    @DeleteMapping(value = "/{id}")
    @Func(code = "delete" ,name = "删除")
    public ResponseData<Void> deleteRegion(@PathVariable String id) {
        CommonRegionDTO.IdDTO dto = new CommonRegionDTO.IdDTO(id);
        commonRegionRpc.deteleRegion(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
}
