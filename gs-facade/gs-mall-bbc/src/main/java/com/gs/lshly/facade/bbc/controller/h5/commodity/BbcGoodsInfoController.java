package com.gs.lshly.facade.bbc.controller.h5.commodity;
import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsSpecInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcSkuGoodInfoVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-23
*/
@RestController
@RequestMapping("/bbc/goodsInfo")
@Api(tags = "2C商城商品信息管理管理-v1.1.0")
@SuppressWarnings({"unchecked","rawtypes"})
public class BbcGoodsInfoController {

    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    @DubboReference
    private IBbcShopRpc bbcShopRpc;

	@ApiOperation("2C商城商品信息管理列表-v1.1.0")
    @GetMapping("")
    public ResponseData<PageData<BbcGoodsInfoVO.GoodsListVO>> pageDataResponseData(BbcGoodsInfoQTO.GoodsListByCategoryQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.pageGoodsListVO(qto));
    }

    @ApiOperation("2C商城商家商品信息管理列表-v1.1.0")
    @GetMapping("/getMerchantGoods")
    public ResponseData<PageData<BbcGoodsInfoVO.GoodsListVO>> pageMerchantGoods(BbcGoodsInfoQTO.MerchantGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.pageMerchantGoods(qto));
    }

    @ApiOperation("2C商城商品信息管理详情-v1.1.0")
    @GetMapping(value = "/{id}")
    public ResponseData<BbcGoodsInfoVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(bbcGoodsInfoRpc.detailGoodsInfo(new BbcGoodsInfoDTO.IdDTO(id)));
    }

    @ApiOperation("搜索2C商城首页商品信息-v1.1.0")
    @PostMapping(value = "getHomeGoods")
    public ResponseData<PageData<BbcGoodsInfoVO.GoodsListVO>> getHomeGoods(@Valid @RequestBody BbcGoodsInfoQTO.GoodsSearchListQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.pageGoodsData(qto));
    }

    @ApiOperation("根据商品所选规格信息获取sku信息-v1.1.0")
    @GetMapping(value = "getSkuVO")
    public ResponseData<BbcSkuGoodInfoVO.SkuVO> getSkuVO(BbcGoodsInfoQTO.GoodsSkuQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getSkuVO(qto));
    }

    @ApiOperation("获取推荐商品列表-v1.1.0")
    @GetMapping(value = "getRecommendGoodsList")
    public ResponseData<PageData<BbcGoodsInfoVO.GoodsListVO>> getRecommendGoodsList(BbcGoodsInfoQTO.OrderGoodsListQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getRecommendGoodsList(qto));
    }

    @ApiOperation("获取店铺推荐商品列表-v1.10")
    @GetMapping(value = "/getShopRecommendGoods")
    public ResponseData<List<BbcGoodsInfoVO.GoodsListVO>> getShopRecommendGoods(BbcGoodsInfoQTO.ShopGoodsIdQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getShopRecommendGoods(qto));
    }

    @ApiOperation("商品分享数据解析")
    @GetMapping(value = "getGoodsSharingVO")
    public ResponseData<BbcGoodsInfoVO.GoodsSharingVO> getGoodsSharingVO(BbcGoodsInfoQTO.GoodsSharingQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getGoodsSharingVO(qto));
    }

    @ApiOperation("in会员抵扣专区商品列表-v1.1.0")
    @PostMapping("/queryInVIPSpecialAreaList")
    public ResponseData<PageData<BbcGoodsInfoVO.InVIPSpecialAreaVO>> queryInVIPSpecialAreaList(BbcGoodsInfoQTO.InSpecialAreaGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.queryInVIPSpecialAreaList(qto));
    }

/*    @ApiOperation("积分商城-我能兑换商品信息列表-v1.1.0（暂时不用）")
    @PostMapping("/queryIntegralGoodsInfo")
    public ResponseData<PageData<BbcGoodsInfoVO.IntegralGoodsInfo>> queryIntegralGoodsInfo(BbcGoodsInfoQTO.IntegralGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.queryIntegralGoodsInfo(qto));
    }*/

    @ApiOperation("查询历史搜索记录-v1.1.0")
    @PostMapping("/getSearchHistory")
    public ResponseData<List<BbcGoodsInfoVO.SearchHistory>> getSearchHistory(@Valid @RequestBody BbcGoodsInfoQTO.SearchHistoryQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getSearchHistory(qto));
    }

	@ApiOperation("清空历史搜索记录-v1.1.0")
    @PostMapping("/emptySearchHistory")
    public ResponseData emptySearchHistory(@Valid @RequestBody BbcGoodsInfoQTO.SearchHistoryQTO qto) {
        bbcGoodsInfoRpc.emptySearchHistory(qto);
        return ResponseData.success();
    }

    @ApiOperation("通用商品信息-v1.1.0")
    @GetMapping("/getGeneralGoodsInfo")
    public ResponseData<List<BbcGoodsInfoVO.GoodsListVO>> getGeneralGoodsInfo() {
        List<BbcGoodsInfoVO.GoodsListVO> resultList = bbcGoodsInfoRpc.getGeneralGoodsInfo();
        return ResponseData.data(resultList);
    }

    @ApiOperation("跟据商品ID获取SKU规格列表-v1.1.0")
    @GetMapping("/listSpecInfoByGoods")
    public ResponseData<List<BbcGoodsSpecInfoVO.SpecListVO>> listSpecInfoByGoods(BbcGoodsInfoQTO.SpecInfoByGoodsQTO qto) {
        List<BbcGoodsSpecInfoVO.SpecListVO> resultList = bbcGoodsInfoRpc.listSpecInfoByGoods(qto);
        return ResponseData.data(resultList);
    }
}
