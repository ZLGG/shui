package com.gs.lshly.facade.platform.controller.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsSpecDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsSpecDictionaryItemRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsSpecDictionaryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/platform/goods-specs-dictionary")
@Api(tags = "平台规格管理")
@Module(code = "item", parent = "category", name = "规格列表", index = 3)
public class GoodsSpecsDictionaryController {
    @DubboReference
    private IGoodsSpecDictionaryRpc dictionaryRpc;

    @DubboReference
    private IGoodsSpecDictionaryItemRpc itemRpc;

    @ApiOperation("添加规格信息")
    @PostMapping(value = "")
    @Func(code="add", name = "新增")
    public ResponseData<Void> addSpec (@RequestBody GoodsSpecDictionaryDTO.ETO addSpecInfoETO){
        dictionaryRpc.addSpec(addSpecInfoETO);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除规格信息")
    @DeleteMapping(value = "/{id}")
    @Func(code="add", name = "删除")
    public ResponseData<Void> deleteSpec (@PathVariable String id){
        GoodsSpecDictionaryDTO.IdDTO idDTO = new GoodsSpecDictionaryDTO.IdDTO(id);
        dictionaryRpc.deleteSpec(idDTO);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("查询规格详情信息")
    @GetMapping(value = "/{id}")
    @Func(code="add", name = "详情")
    public @ResponseBody ResponseData<GoodsSpecDictionaryVO.DetailVO> getSpecDetails(@PathVariable String id, GoodsSpecDictionaryItemQTO.QTO qto){
        GoodsSpecDictionaryDTO.IdDTO dto = new GoodsSpecDictionaryDTO.IdDTO(id);
        GoodsSpecDictionaryVO.DetailVO detailVO = dictionaryRpc.getSpecDetails(dto);
        qto.setSpecId(id);
        detailVO.setList(itemRpc.listSpecItem(qto).getContent());
        return ResponseData.data(detailVO);
    }

    @ApiOperation("修改规格信息")
    @PutMapping(value = "/{id}")
    @Func(code="add", name = "修改")
    public ResponseData<Void> getSpecDetails(@PathVariable String id,@RequestBody GoodsSpecDictionaryDTO.ETO dto){
        dto.setId(id);
        dictionaryRpc.updateSpec(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("查询规格列表")
    @GetMapping(value = "")
    @Func(code="add", name = "查询")
    public ResponseData<PageData<GoodsSpecDictionaryVO.ListVO>> listpec(){
        GoodsSpecDictionaryQTO.QTO qto = new GoodsSpecDictionaryQTO.QTO();
        return ResponseData.data(dictionaryRpc.listSpec(qto));
    }

    @ApiOperation("批量删除规格列表")
    @DeleteMapping(value = "/deleteSpecList")
    @Func(code="add", name = "批量删除")
    public ResponseData<Void> deleSpecList(GoodsSpecDictionaryDTO.IdListDTO dto){
       dictionaryRpc.deleteSpecBatches(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("查询类目关联的规格列表")
    @GetMapping(value = "/listSpecInfo/{id}")
    public ResponseData<List<GoodsSpecDictionaryVO.ListVO>> listSpecInfo(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        return ResponseData.data(dictionaryRpc.listSpecInfo(dto));
    }

    @ApiOperation("查询类目关联的规格以及规格详情列表")
    @GetMapping(value = "/listSpecDetail/{id}")
    public ResponseData<List<GoodsSpecDictionaryVO.DetailVO>> listSpecDetail(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        return ResponseData.data(dictionaryRpc.listSpecDetail(dto));
    }

    @ApiOperation("查询规格以及规格详情列表")
    @GetMapping(value = "/listSpecInfos")
    public ResponseData<List<GoodsSpecDictionaryVO.DetailVO>> listSpecInfos(){
        return ResponseData.data(dictionaryRpc.listSpecInfos());
    }
}
