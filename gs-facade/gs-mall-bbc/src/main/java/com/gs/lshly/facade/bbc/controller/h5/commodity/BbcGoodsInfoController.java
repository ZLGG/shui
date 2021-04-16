package com.gs.lshly.facade.bbc.controller.h5.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.dto.BbcGoodsInfoDTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcSkuGoodInfoVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


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

    @ApiOperation("搜素2C商城首页商品信息-v1.1.0")
    @PostMapping(value = "getHomeGoods")
    public ResponseData<PageData<BbcGoodsInfoVO.GoodsListVO>> getHomeGoods(@Valid @RequestBody BbcGoodsInfoQTO.GoodsListQTO qto) {
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

    @ApiOperation("查询搜索历史记录-v1.1.0")
    @GetMapping("/getSearchHistory")
    public ResponseData<BbcGoodsInfoVO.SearchHistory> getSearchHistory(@Valid @RequestBody BbcGoodsInfoQTO.SearchHistoryQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.getSearchHistory(qto));
    }
}
