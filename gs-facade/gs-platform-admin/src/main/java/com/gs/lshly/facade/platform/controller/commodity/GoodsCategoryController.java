package com.gs.lshly.facade.platform.controller.commodity;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsParamsDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsCategoryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsCategoryRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsParamsRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author Starry
 * @Date 15:23 2020/9/27
 */
@RestController
@RequestMapping("/platform/goods-category")
@Api(tags = "平台商品分类管理")
@Module(code = "listSort", parent = "category", name = "产品分类", index =4)
public class GoodsCategoryController {

    @DubboReference
    private IGoodsCategoryRpc goodsCategoryRpc;
    @DubboReference
    private IGoodsParamsRpc goodsParamsRpc;

    @ApiOperation("添加一级分类")
    @PostMapping(value = "/addLevel1")
    @Func(code="add", name="新增")
    public ResponseData<Void> addLevel1CateGory(@RequestBody GoodsCategoryDTO.Level1ETO eto){
        goodsCategoryRpc.addCategory(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("添加二级分类")
    @PostMapping(value = "/addLevel2")
    @Func(code="add", name="新增")
    public ResponseData<Void> addLevel2CateGory(@RequestBody GoodsCategoryDTO.Level2ETO eto){
        goodsCategoryRpc.addCategory(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("添加三级分类")
    @PostMapping(value = "/addLevel3")
    @Func(code="add", name="新增")
    public ResponseData<Void> addLevel3CateGory(@RequestBody GoodsCategoryDTO.Level3ETO eto){
        goodsCategoryRpc.addCategory(eto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("查询类目的父分类信息")
    @GetMapping(value = "/getParentCategoryVO/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<GoodsCategoryVO.ParentCategoryVO> getParentCategoryVO(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        GoodsCategoryVO.ParentCategoryVO parentCategoryVO = goodsCategoryRpc.getParentCategory(dto);
        return ResponseData.data(parentCategoryVO);
    }

    @ApiOperation("查询一级分类详情信息")
    @GetMapping(value = "/getLevel1Detail/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<GoodsCategoryVO.Level1VO> getCategoryDetail(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        GoodsCategoryVO.Level1VO level1VO = goodsCategoryRpc.selectLevel1VO(dto);
        return ResponseData.data(level1VO);
    }

    @ApiOperation("查询二级分类详情信息")
    @GetMapping(value = "/getLevel2Detail/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<GoodsCategoryVO.Level2VO> getLevel2Detail(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        GoodsCategoryVO.Level2VO level2VO = goodsCategoryRpc.selectLevel2VO(dto);
        return ResponseData.data(level2VO);
    }

    @ApiOperation("查询三级分类详情信息")
    @GetMapping(value = "/getLevel3Detail/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<GoodsCategoryVO.Level3VO> getLevel3Detail(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        GoodsCategoryVO.Level3VO level3VO = goodsCategoryRpc.selectLevel3VO(dto);
        return ResponseData.data(level3VO);
    }

    @ApiOperation("分类列表")
    @GetMapping(value = "/listCategory")
    @Func(code="view", name = "查看")
    public ResponseData<List<GoodsCategoryVO.CategoryTreeVO>> listCategory(){
        List<GoodsCategoryVO.CategoryTreeVO> categoryTree = goodsCategoryRpc.selectCategoryTree();
        return ResponseData.data(categoryTree);
    }

    @ApiOperation("分类二级列表")
    @GetMapping(value = "/listleve2Category/{applyId}")
    @Func(code="view", name = "查看")
    public ResponseData<List<GoodsCategoryVO.ListVO>> listleve2Category(@PathVariable String applyId){
        List<GoodsCategoryVO.ListVO> categoryTreeVOList = goodsCategoryRpc.level2Categories(new GoodsCategoryDTO.ApplyIdDTO(applyId));
        return ResponseData.data(categoryTreeVOList);
    }

    @ApiOperation("修改一级分类信息")
    @PutMapping(value = "/updateLevel1Category/{id}")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> updateLevel1Category(@PathVariable String id,@RequestBody GoodsCategoryDTO.Level1ETO eto){
        eto.setId(id);
        goodsCategoryRpc.updateCategory(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("修改二级分类信息")
    @PutMapping(value = "/updateLevel2Category/{id}")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> updateLevel2Category(@PathVariable String id,@RequestBody GoodsCategoryDTO.Level2ETO eto){
        eto.setId(id);
        goodsCategoryRpc.updateCategory(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("修改三级分类信息")
    @PutMapping(value = "/updateLevel3Category/{id}")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> updateLevel3Category(@PathVariable String id,@RequestBody GoodsCategoryDTO.Level3ETO eto){
        eto.setId(id);
        goodsCategoryRpc.updateCategory(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("删除分类信息")
    @DeleteMapping(value = "/{id}")
    @Func(code="delete", name = "删除")
    public ResponseData<Void> deleteCategory(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        goodsCategoryRpc.deleteCategory(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("编辑类目排序")
    @PutMapping(value = "/changeIdx")
    @Func(code="edit", name = "编辑")
    public ResponseData<Void> changeIdx(@RequestBody List<GoodsCategoryDTO.IdxETO> idxETOS){
        goodsCategoryRpc.updateIdx(idxETOS);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("设置商品使用范围")
    @PutMapping(value = "/setManage/{id}")
    @Func(code="edit", name = "编辑")
    public ResponseData<Void> setUseFiled(@PathVariable String id,@RequestBody GoodsCategoryDTO.UseFiledETO eto){
        eto.setId(id);
        goodsCategoryRpc.updateUseFile(eto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("查询三级分类")
    @Func(code="view", name = "查看")
    @GetMapping(value = "/findLevel3/{id}")
    public ResponseData<PageData<GoodsCategoryVO.Level3VO>> findLevel3(@PathVariable String id,GoodsCategoryQTO.QTO qto){
        qto.setId(id);
        return ResponseData.data(goodsCategoryRpc.selectLevel3(qto));
    }
    @ApiOperation("关联属性")
    @PutMapping(value ="/bindAttribute/{id}")
    @Func(code="add", name = "新增")
    public ResponseData<Void> bindAttribute(@PathVariable String id, @RequestBody GoodsAttributeDictionaryDTO.IdListDTO dto){
        GoodsCategoryDTO.IdDTO idDTO = new GoodsCategoryDTO.IdDTO(id);
        goodsCategoryRpc.bindAttribute(dto,idDTO);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }
    @ApiOperation("关联规格")
    @PutMapping(value ="/bindSpec/{id}")
    @Func(code="add", name = "新增")
    public ResponseData<Void> bindSpec(@PathVariable String id, @RequestBody GoodsSpecDictionaryDTO.IdListDTO idListDTO){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        goodsCategoryRpc.bindSpec(idListDTO,dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }
    @ApiOperation("关联品牌")
    @PutMapping(value ="/bindBrand/{id}")
    @Func(code="add", name = "新增")
    public ResponseData<Void> bindBrand(@PathVariable String id, @RequestBody GoodsBrandDTO.IdListDTO idListDTO){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        goodsCategoryRpc.bindBrand(idListDTO,dto);
        return ResponseData.success(MsgConst.SAVE_SUCCESS);
    }

    @ApiOperation("关联参数")
    @PutMapping(value ="/bindParams/{id}")
    @Func(code="add", name = "新增")
    public ResponseData<Void> bindParams(@PathVariable String id, @RequestBody List<GoodsParamsDTO.ETO> etoList){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        goodsParamsRpc.addGoodsParams(dto,etoList);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("导出类目数据到Excel表格")
    @GetMapping(value = "/exportData")
    @Func(code="export", name = "导出")
    public void export(@ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = goodsCategoryRpc.export();
        ExcelUtil.export(exportData, response);

    }

    @ApiOperation("关联查询该店铺所有的二级分类或者三级分类")
    @GetMapping(value = "/joinSearch/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<List<GoodsCategoryVO.CategoryJoinSearchVO>> joinSearch(@PathVariable String id){
       GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        return ResponseData.data(goodsCategoryRpc.listCategoryLevels(dto));
    }

    @ApiOperation("关联查询该店铺所有的一级")
    @GetMapping(value = "/joinQuery")
    @Func(code="view", name = "查看")
    public ResponseData<List<GoodsCategoryVO.CategoryJoinSearchVO>> joinQuery(GoodsCategoryDTO.IdListDTO dto){
        return ResponseData.data(goodsCategoryRpc.listCategoryLevel1(dto));
    }

    @ApiOperation("查询商品一级分类")
    @GetMapping("listLevel1Categories")
    @Func(code="view", name = "查看")
    public ResponseData<List<GoodsCategoryVO.ListVO>> listLevel1Categories() {
        return ResponseData.data(goodsCategoryRpc.level1Categories());
    }

    @ApiOperation("查询商品一级分类下的子分类")
    @GetMapping("getChildrenCategories/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<GoodsCategoryVO.CategoryTreeVO> getChildrenCategories(@PathVariable String id) {
        return ResponseData.data(goodsCategoryRpc.categoryTree(new GoodsCategoryDTO.IdDTO(id)));
    }
}
