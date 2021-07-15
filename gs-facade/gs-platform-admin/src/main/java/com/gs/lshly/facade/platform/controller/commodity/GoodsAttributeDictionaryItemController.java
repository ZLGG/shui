package com.gs.lshly.facade.platform.controller.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsAttributeDictionaryItemDTO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsAttributeDictionaryItemRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/platform/goods-attribute-dictionary-item")
@Api(tags = "平台属性值管理")
//@Module(code = "listAttribute", parent = "category", name = "规格列表", index = 2)
public class GoodsAttributeDictionaryItemController {

    @DubboReference
    IGoodsAttributeDictionaryItemRpc itemRpc;

    @ApiOperation("删除属性值信息")
    @DeleteMapping(value = "/{id}")
//    @Func(code="delete", name="删除")
    public ResponseData<Void> deleteAttributeItemInfo(@PathVariable String id) {
        GoodsAttributeDictionaryItemDTO.IdDTO dto = new GoodsAttributeDictionaryItemDTO.IdDTO(id);
        itemRpc.deleteAttributeItem(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
}
