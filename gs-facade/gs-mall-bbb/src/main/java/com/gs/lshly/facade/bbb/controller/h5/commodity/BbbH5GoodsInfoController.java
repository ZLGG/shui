package com.gs.lshly.facade.bbb.controller.h5.commodity;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.dto.BbbH5GoodsInfoDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.qto.BbbH5GoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsSpecInfoVO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5SkuGoodInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.h5.merchant.IBbbH5ShopRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;


/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-23
*/
@RestController
@RequestMapping("/bbb/h5/goodsInfo")
@Api(tags = "2B商城商品信息管理管理-V1.1.0")
public class BbbH5GoodsInfoController {

    @DubboReference
    private IBbbH5GoodsInfoRpc bbcGoodsInfoRpc;
    @DubboReference
    private IBbbH5ShopRpc bbcShopRpc;

    @ApiOperation("2B商城商品信息管理列表-V1.1.0")
    @PostMapping("")
    public ResponseData<PageData<BbbH5GoodsInfoVO.GoodsListVO>> pageDataResponseData(@RequestBody  BbbH5GoodsInfoQTO.GoodsListByCategoryQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.pageGoodsListVO(qto));
    }

    @ApiOperation("2B商家商品信息管理列表")
    @PostMapping("/merchantGoodsInfo")
    public ResponseData<PageData<BbbH5GoodsInfoVO.GoodsListVO>> pageDataMerchantGoodsInfo(@RequestBody  BbbH5GoodsInfoQTO.MerchantShopGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.pageMerchantGoodsListVO(qto));
    }


    @ApiOperation("2B商城商品信息管理详情-V1.1.0")
    @GetMapping(value = "detail/{id}")
    public ResponseData<BbbH5GoodsInfoVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(bbcGoodsInfoRpc.detailGoodsInfo(new BbbH5GoodsInfoDTO.IdDTO(id)));
    }


    @ApiOperation("根据商品id获取规格列表")
    @GetMapping(value = "/{goodsId}")
    public ResponseData<BbbH5GoodsSpecInfoVO.SpecListVO> getSpecListVO(@PathVariable String goodsId) {
        BbbH5GoodsInfoDTO.IdDTO dto = new BbbH5GoodsInfoDTO.IdDTO(goodsId);
        return ResponseData.data(bbcGoodsInfoRpc.listSpecVOs(dto));
    }

    @ApiOperation("根据商品所选规格信息获取sku信息-V1.1.0")
    @PostMapping(value = "getSkuVO")
    public ResponseData<BbbH5SkuGoodInfoVO.SkuVO> getSkuVO(@RequestBody BbbH5GoodsInfoQTO.GoodsSkuQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getSkuVO(qto));
    }

    @ApiOperation("获取推荐商品列表-V1.1.0")
    @PostMapping(value = "getRecommendGoodsList")
    public ResponseData<PageData<BbbH5GoodsInfoVO.GoodsListVO>> getRecommendGoodsList(@RequestBody BbbH5GoodsInfoQTO.GoodsListQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getRecommendGoodsList(qto));
    }

    @ApiOperation("通过一级类目查询商品（快捷下单）-V1.1.0")
    @PostMapping(value = "getQuickOrder")
    public ResponseData<PageData<BbbH5GoodsInfoVO.GoodsListVO>> getQuickOrder(@RequestBody BbbH5GoodsInfoQTO.QuickOrderQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getQuickOrderGoodsList(qto));
    }

    @ApiOperation("in会员抵扣专区商品列表-v1.1.0")
    @PostMapping("/queryInVIPSpecialAreaList")
    public ResponseData<PageData<BbbH5GoodsInfoVO.InVIPSpecialAreaVO>> queryInVIPSpecialAreaList(BbbH5GoodsInfoQTO.InSpecialAreaGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.queryInVIPSpecialAreaList(qto));
    }

    @ApiOperation("积分商城-我能兑换商品信息列表-v1.1.0")
    @PostMapping("/queryIntegralGoodsInfo")
    public ResponseData<PageData<BbbH5GoodsInfoVO.IntegralGoodsInfo>> queryIntegralGoodsInfo(BbbH5GoodsInfoQTO.IntegralGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.queryIntegralGoodsInfo(qto));
    }

}
