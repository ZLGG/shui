package com.gs.lshly.facade.platform.controller.merchant;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.merchant.dto.ShopGoodsCategoryDTO;
import com.gs.lshly.common.struct.platadmin.merchant.qto.ShopGoodsCategoryQTO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopGoodsCategoryVO;
import com.gs.lshly.rpc.api.platadmin.merchant.IShopGoodsCategoryRpc;
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
* @author xxfc
* @since 2020-10-16
*/
@RestController
@RequestMapping("/platform/shopGoodsCategory")
@Api(tags = "店铺商品类目费用管理",description = " ")
public class ShopGoodsCategoryController {

    @DubboReference
    private IShopGoodsCategoryRpc shopGoodsCategoryRpc;

    @ApiOperation("店铺商品类目列表")
    @GetMapping("/{shopId}")
    public ResponseData<List<ShopGoodsCategoryVO.GoodsCategoryOneVO>> list(@PathVariable String shopId) {
        return ResponseData.data(shopGoodsCategoryRpc.list(new ShopGoodsCategoryQTO.ShopIdQTO(shopId)));
    }

    @ApiOperation("编辑商品类目费用")
    @PutMapping("/editorPatchPrice")
    public ResponseData<Void> editShopGoodsCategoryPrice(@Valid @RequestBody ShopGoodsCategoryDTO.ListPriceETO eto) {
        shopGoodsCategoryRpc.editShopGoodsCategoryPrice(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }


}
