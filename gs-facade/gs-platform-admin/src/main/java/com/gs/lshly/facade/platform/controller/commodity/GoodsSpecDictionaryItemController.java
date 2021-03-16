package com.gs.lshly.facade.platform.controller.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsSpecDictionaryItemDTO;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsSpecDictionaryItemRpc;
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
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/platform/goods-spec-dictionary-item")
@Api(tags = "平台规格值管理")
public class GoodsSpecDictionaryItemController {

    @DubboReference
    private IGoodsSpecDictionaryItemRpc specDictionaryItemRpc;

    @ApiOperation("删除规格值")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> deleteSpecItem (@PathVariable String id){
        GoodsSpecDictionaryItemDTO.IdDTO dto = new GoodsSpecDictionaryItemDTO.IdDTO(id);
        specDictionaryItemRpc.deleteSpecItem(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }
}
