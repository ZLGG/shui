package com.gs.lshly.facade.bbb.controller.h5.merchant;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.merchant.dto.BbbH5ShopDTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.qto.BbbH5ShopQTO;
import com.gs.lshly.common.struct.bbb.h5.merchant.vo.BbbH5ShopVO;
import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import com.gs.lshly.rpc.api.common.ICommonShopRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-11-03
*/
@RestController
@RequestMapping("/bbb/h5/shop")
@Api(tags = "店铺管理")
public class BbbH5ShopController {

    @DubboReference
    private IBbbH5ShopRpc bbbH5ShopRpc;

    @DubboReference
    private ICommonShopRpc commonShopRpc;

    @ApiOperation("店铺组合信息")
    @GetMapping(value = "/shopComplex/{shopId}")
    public ResponseData<BbbH5ShopVO.ComplexVO> shopComplex(@PathVariable String shopId) {
        return ResponseData.data(bbbH5ShopRpc.shopComplex(new BbbH5ShopDTO.IdDTO(shopId)));
    }

    @ApiOperation("店铺楼层商品(最大商品数量pageNum=1,pageSize = 最大数量)")
    @GetMapping(value = "/shopFloorGoods/{floorId}")
    public ResponseData<PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO>> shopFloor(@PathVariable String floorId, BbbH5ShopQTO.FloorGoodsQTO qto) {
        qto.setFloorId(floorId);
        return ResponseData.data(bbbH5ShopRpc.shopFloorGoods(qto));
    }

    @ApiOperation("店铺详情")
    @GetMapping(value = "/{shopId}")
    public ResponseData<BbbH5ShopVO.DetailVO> getDetail(@PathVariable String shopId) {
        return ResponseData.data(bbbH5ShopRpc.detailShop(new BbbH5ShopDTO.IdDTO(shopId)));
    }

    @ApiOperation("店铺搜索列表")
    @GetMapping("")
    public ResponseData<PageData<BbbH5ShopVO.ListVO>> list(BbbH5ShopQTO.QTO qto) {
        return ResponseData.data(bbbH5ShopRpc.pageData(qto));
    }

    @ApiOperation("店铺客服")
    @GetMapping("/service/{id}")
    public ResponseData<CommonShopVO.ShopServiceOutVO> service(@PathVariable String id) {
        return ResponseData.data(commonShopRpc.getShopService(id));
    }


}
