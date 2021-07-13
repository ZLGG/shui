package com.gs.lshly.facade.platform.controller.commodity;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsMaterialLibraryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsMaterialLibraryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsMaterialLibraryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsMaterialLibraryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Starry
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/platform/goodsMaterialLibrary")
@Api(tags = "商品素材库管理管理")
@Module(code = "materialLibrary", parent = "commodity", name = "商品素材库", index = 2)
public class GoodsMaterialLibraryController {

    @DubboReference
    private IGoodsMaterialLibraryRpc GoodsMaterialLibraryRpc;

    @ApiOperation("商品素材库管理列表")
    @GetMapping("")
    @Func(code = "view", name = "查看列表")
    public ResponseData<PageData<GoodsMaterialLibraryVO.DetailListVO>> list(GoodsMaterialLibraryQTO.QTO qto) {
        return ResponseData.data(GoodsMaterialLibraryRpc.pageData(qto));
    }

    @ApiOperation("商品素材库管理详情")
    @GetMapping(value = "/{id}")
    @Func(code = "detail", name = "查看详情")
    public ResponseData<GoodsMaterialLibraryVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(GoodsMaterialLibraryRpc.detailGoodsMaterialLibrary(new GoodsMaterialLibraryDTO.IdDTO(id)));
    }

    @ApiOperation("新增商品素材库管理")
    @PostMapping("")
    @Func(code = "add", name = "新增")
    public ResponseData<Void> add(@Valid @RequestBody GoodsMaterialLibraryDTO.ETO dto) {
        GoodsMaterialLibraryRpc.addGoodsMaterialLibrary(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商品素材库管理")
    @PostMapping(value = "deleteBatch")
    @Func(code = "delete", name = "删除")
    public ResponseData<Void> delete(@RequestBody GoodsMaterialLibraryDTO.IdListDTO dto) {
        GoodsMaterialLibraryRpc.deleteGoodsMaterialLibrary(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


    @ApiOperation("修改商品素材库管理")
    @PutMapping(value = "/{id}")
    @Func(code = "edit", name = "修改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody GoodsMaterialLibraryDTO.ETO eto) {
        eto.setId(id);
        GoodsMaterialLibraryRpc.editGoodsMaterialLibrary(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("导出类目数据到Excel表格")
    @GetMapping(value = "/exportData")
    @Func(code = "export", name = "导出")
    public void export(@ApiIgnore HttpServletResponse response, GoodsMaterialLibraryDTO.IdListDTO dto) throws Exception {
        ExportDataDTO exportData = GoodsMaterialLibraryRpc.export(dto);
        ExcelUtil.export(exportData, response);
    }

}
