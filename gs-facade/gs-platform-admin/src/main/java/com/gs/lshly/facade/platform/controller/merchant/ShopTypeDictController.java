package com.gs.lshly.facade.platform.controller.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopTypeDictDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopTypeDictQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopTypeDictVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopTypeDictRpc;
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
* @author xxfc
* @since 2020-10-14
*/
@RestController
@RequestMapping("/platform/shopTypeDict")
@Api(tags = "店铺类型管理",description = " ")
@Module(code = "listShopType", parent = "bussiness", name = "店铺类型列表", index = 3)
public class ShopTypeDictController {

    @DubboReference
    private IShopTypeDictRpc shopTypeDictRpc;

    @ApiOperation("店铺类型列表")
    @GetMapping("")
    @Func(code="view", name="查")
    public ResponseData<PageData<ShopTypeDictVO.ListVO>> list(ShopTypeDictQTO.QTO qto) {
        return ResponseData.data(shopTypeDictRpc.pageData(qto));
    }

    @ApiOperation("编辑店铺类型")
    @PutMapping(value = "/{id}")
    @Func(code="edit", name="改")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody ShopTypeDictDTO.ETO eto) {
        eto.setId(id);
        shopTypeDictRpc.editShopTypeDict(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

}
