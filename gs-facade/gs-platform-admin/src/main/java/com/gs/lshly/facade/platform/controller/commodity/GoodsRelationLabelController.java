package com.gs.lshly.facade.platform.controller.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsRelationLabelDTO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsRelationLabelRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-15
*/
@RestController
@RequestMapping("/platform/goodsRelationLabel")
@Api(tags = "商品标签关系表管理")
@Module(code = "goodsRelationLabel", parent = "labelManagement", name = "商品标签管理", index =1)
public class GoodsRelationLabelController {

    @DubboReference
    private IGoodsRelationLabelRpc goodsRelationLabelRpc;


    @ApiOperation("批量建立商品与商品标签关系表")
    @PostMapping("")
    @Func(code = "add",name = "新增")
    public ResponseData<Void> add(@Valid @RequestBody GoodsRelationLabelDTO.ETO dto) {
        goodsRelationLabelRpc.addGoodsRelationLabel(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

}
