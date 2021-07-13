package com.gs.lshly.facade.platform.controller.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryItemQTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsAttributeDictionaryQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsAttributeDictionaryVO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsAttributeDictionaryItemRpc;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsAttributeDictionaryRpc;
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
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/platform/goods-attribute-dictionary")
@Api(tags = "平台属性管理")
@Module(code = "item", parent = "category", name = "规格列表", index = 2)
public class GoodsAttributeDictionaryController {

    @DubboReference
    IGoodsAttributeDictionaryRpc attributeDictionaryRpc;
    @DubboReference
    IGoodsAttributeDictionaryItemRpc attributeDictionaryItemRpc;

    @ApiOperation("新增属性信息")
    @PostMapping("")
    @Func(code="add", name = "新增")
    public ResponseData<Void> addAttribute(@RequestBody GoodsAttributeDictionaryDTO.ETO dto) {
        attributeDictionaryRpc.addGoodsAttributeDictionary(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除属性信息")
    @DeleteMapping(value = "/{id}")
    @Func(code="delete", name = "删除")
    public ResponseData<Void> deleteAttribute(@PathVariable String id) {
        GoodsAttributeDictionaryDTO.IdDTO dto = new GoodsAttributeDictionaryDTO.IdDTO(id);
        attributeDictionaryRpc.deleteAttribute(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("获取属性详情信息")
    @GetMapping(value = "/{id}")
    @Func(code="detail", name = "查看详情")
    public @ResponseBody ResponseData<GoodsAttributeDictionaryVO.DetailVO> getAttributeDetails(@PathVariable String id, GoodsAttributeDictionaryItemQTO.QTO qto) {
        GoodsAttributeDictionaryDTO.IdDTO dto = new GoodsAttributeDictionaryDTO.IdDTO(id);
        GoodsAttributeDictionaryVO.DetailVO detailVO = attributeDictionaryRpc.getAttributeDetails(dto);
        qto.setAttributeId(dto.getId());
        detailVO.setList(attributeDictionaryItemRpc.listAttributeValue(qto).getContent());
        return ResponseData.data(detailVO);
    }

    @ApiOperation("获取属性列表")
    @GetMapping(value = "")
    @Func(code="viewList", name = "查看列表")
    public ResponseData<PageData<GoodsAttributeDictionaryVO.ListVO>> listAttributeInfo(){
        GoodsAttributeDictionaryQTO.QTO qto = new GoodsAttributeDictionaryQTO.QTO();
        return ResponseData.data(attributeDictionaryRpc.listAttribute(qto));
    }

    @ApiOperation("修改属性信息")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> updateAttributeInfo(@PathVariable String id,@RequestBody GoodsAttributeDictionaryDTO.ETO dto){
        dto.setId(id);
        attributeDictionaryRpc.updateAttribute(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("批量删除属性信息")
    @Func(code="delete", name = "删除")
    @DeleteMapping(value = "/deleteAttributeList")
    public ResponseData<Void> deleteAttributeList(GoodsAttributeDictionaryDTO.IdListDTO dto) {
       attributeDictionaryRpc.deleteAttributeBatch(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("获取跟类目关联的属性信息")
    @GetMapping(value = "/listAttribute/{id}")
    public ResponseData<List<GoodsAttributeDictionaryVO.ListVO>> listAttribute(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        return ResponseData.data(attributeDictionaryRpc.listAttributes(dto));
    }

    @ApiOperation("获取跟类目关联的属性以及属性详情信息")
    @GetMapping(value = "/listAttributes/{id}")
    public ResponseData<List<GoodsAttributeDictionaryVO.DetailVO>> listAttributes(@PathVariable String id){
        GoodsCategoryDTO.IdDTO dto = new GoodsCategoryDTO.IdDTO(id);
        return ResponseData.data(attributeDictionaryRpc.listAttributeDetail(dto));
    }

    @ApiOperation("查询所有的属性以及属性值列表")
    @GetMapping(value = "/listAttributeDetails")
    public ResponseData<List<GoodsAttributeDictionaryVO.DetailVO>> listAttributeDetails(){
        return ResponseData.data(attributeDictionaryRpc.listAttributes());
    }
}
