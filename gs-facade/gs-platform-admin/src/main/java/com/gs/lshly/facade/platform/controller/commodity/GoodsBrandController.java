package com.gs.lshly.facade.platform.controller.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsBrandDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsBrandQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsBrandRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 商品品牌 前端控制器
 * </p>
 *
 * @author
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/platform/goods-brand")
@Api(tags = "平台品牌管理")
@Module(code = "listBrand", parent = "category", name = "品牌列表", index = 1)
public class GoodsBrandController {

    @DubboReference
    IGoodsBrandRpc goodsBrandRpc;

    @ApiOperation("新增品牌信息")
    @PostMapping("")
    @Func(code="add", name="新增")
    public ResponseData<Void> add(@RequestBody GoodsBrandDTO.ETO dto) {
        goodsBrandRpc.addGoodsBrand(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除品牌信息")
    @DeleteMapping(value = "deleteList")
    @Func(code="delete", name="删除")
    public ResponseData<Void> delete(@RequestParam String id) {
        List<String> attributeIdList = Arrays.asList(id.split(","));
        if (attributeIdList.size()>0){
            for (String attributeId:attributeIdList) {
                GoodsBrandDTO.IdDTO dto = new GoodsBrandDTO.IdDTO(attributeId);
                goodsBrandRpc.deleteGoodsBrand(dto);
            }
        }
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("修改品牌信息")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> update(@PathVariable String id, @RequestBody GoodsBrandDTO.ETO dto) {
        dto.setId(id);
        goodsBrandRpc.updateGoodsBrand(dto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("查询所有品牌信息")
    @GetMapping("")
    @Func(code="view", name = "查看")
    public ResponseData<PageData<GoodsBrandVO.ListVO>> list(GoodsBrandQTO.QTO qo) {
        return ResponseData.data(goodsBrandRpc.list(qo));
    }

    @ApiOperation("根据品牌id查询品牌信息")
    @GetMapping(value = "/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<GoodsBrandVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(goodsBrandRpc.getGoodsBrand(new GoodsBrandDTO.IdDTO(id)));
    }

    @ApiOperation("查询类目关联的品牌信息")
    @GetMapping(value = "/listBrand/{id}")
    @Func(code="view", name = "查看")
    public ResponseData<List<GoodsBrandVO.ListVO>> getList(@PathVariable String id) {
        return ResponseData.data(goodsBrandRpc.listGoodsBrand(new GoodsCategoryDTO.IdDTO(id)));
    }

    @ApiOperation("保存品牌信息并返回相应信息")
    @PostMapping("/getId")
    @Func(code="add", name = "新增")
    public ResponseData<Void> addToGetId(@RequestBody GoodsBrandDTO.ETO dto) {
        return ResponseData.data(goodsBrandRpc.saveBrand(dto));
    }
}
