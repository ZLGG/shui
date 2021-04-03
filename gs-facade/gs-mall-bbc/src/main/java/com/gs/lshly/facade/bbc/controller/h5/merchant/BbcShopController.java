package com.gs.lshly.facade.bbc.controller.h5.merchant;
import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.merchant.dto.BbcShopDTO;
import com.gs.lshly.common.struct.bbc.merchant.qto.BbcShopQTO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopNavigationVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.common.dto.CommonMerchantArticleDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantArticleQTO;
import com.gs.lshly.rpc.api.bbc.merchant.IBbcShopRpc;
import com.gs.lshly.rpc.api.common.ICommonMerchantArticleRpc;
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
* @since 2020-11-03
*/
@RestController
@RequestMapping("/bbc/shop")
@Api(tags = "店铺管理")
public class BbcShopController {

    @DubboReference
    private IBbcShopRpc bbcShopRpc;
    @DubboReference
    private ICommonMerchantArticleRpc commonMerchantArticleRpc;

    @ApiOperation("店铺列表")
    @GetMapping("")
    public ResponseData<PageData<BbcShopVO.ListVO>> list(BbcShopQTO.QTO qto) {
        return ResponseData.data(bbcShopRpc.pageData(qto));
    }

    @ApiOperation("附近门店")
    @GetMapping("/scopeList")
    public ResponseData<PageData<BbcShopVO.ScopeListVO>> nearShopList(BbcShopQTO.ScopeQTO qto) {
        return ResponseData.data(bbcShopRpc.nearShopListPageData(qto));
    }

    @ApiOperation("附近门店(附近10公里)")
    @GetMapping("/nearShop")
    public ResponseData<List<BbcShopVO.ScopeListVO>> nearShop(BbcShopQTO.ScopeQTO qto) {
        return ResponseData.data(bbcShopRpc.nearShopList(qto));
    }

    @ApiOperation("店铺详情")
    @GetMapping(value = "/{shopId}")
    public ResponseData<BbcShopVO.DetailVO> get(@PathVariable String shopId) {
        return ResponseData.data(bbcShopRpc.detailShop(new BbcShopDTO.IdDTO(shopId)));
    }

    @ApiOperation("店铺组合信息-v1.1.0")
    @GetMapping(value = "/shopComplex/{shopId}")
    public ResponseData<BbcShopVO.ComplexVO> shopComplex(@PathVariable String shopId) {
        return ResponseData.data(bbcShopRpc.shopComplex(new BbcShopDTO.IdDTO(shopId)));
    }


    @ApiOperation("店铺分类列表")
    @GetMapping(value = "/navigation/{shopId}")
    public ResponseData<List<BbcShopVO.ShopNavVO>> navigationTree(@PathVariable String shopId) {
        return ResponseData.data(bbcShopRpc.navigationTree(new BbcShopDTO.IdDTO(shopId)));
    }

    @ApiOperation("是否同一城市")
    @PostMapping(value = "/navigation/isCity")
    public ResponseData<BbcShopVO.isCity> isCity(@Valid @RequestBody BbcShopDTO.isCity dto) {
        return ResponseData.data(bbcShopRpc.isCity(dto));
    }

    @ApiOperation("2c店铺分类列表")
    @PostMapping(value = "/listNavigationListVO/{shopId}")
    public ResponseData<BbcShopNavigationVO.NavigationListVO> listNavigationListVO(@PathVariable String shopId) {
        return ResponseData.data(bbcShopRpc.listNavigationListVO(new BbcShopDTO.IdDTO(shopId)));
    }

    @ApiOperation("获取店铺ID")
    @GetMapping(value = "/getShopId/{shopNavigationId}")
    public ResponseData<BbcShopVO.ShopIdName> getShopId(@PathVariable String shopNavigationId) {
        return ResponseData.data(bbcShopRpc.getShopIdName(new BbcShopDTO.ShopNavigationIdDTO(shopNavigationId)));
    }

    @ApiOperation("商家文章列表")
    @GetMapping(value = "/getMerchantArticle/{shopId}")
    public ResponseData<BbcShopVO.ShopIdName> getMerchantArticle(@PathVariable String shopId, CommonMerchantArticleQTO.QTO qto) {
        qto.setShopId(shopId);
        qto.setPcShow(PcH5Enum.YES.getCode());
        return ResponseData.data(commonMerchantArticleRpc.pageMerchantArticleVO(qto));
    }

    @ApiOperation("商家文章详情")
    @GetMapping(value = "/getMerchantArticleDetail/{articleId}")
    public ResponseData<BbcShopVO.ShopIdName> getMerchantArticleDetail(@PathVariable String articleId) {
        return ResponseData.data(commonMerchantArticleRpc.detailMerchantArticle(new CommonMerchantArticleDTO.IdDTO(articleId)));
    }


//
//    @ApiOperation("店铺文本导航")
//    @GetMapping(value = "/textMenuList")
//    public ResponseData<BbcShopVO.DetailVO> textMenuList() {
//        return ResponseData.data(bbcShopRpc.textMenuList(new BbcShopDTO.IdDTO(id)));
//    }
//
//    @ApiOperation("店铺菜单导航")
//    @GetMapping(value = "/menuList")
//    public ResponseData<BbcShopVO.DetailVO> menuList() {
//        return ResponseData.data(bbcShopRpc.menuList(new BbcShopDTO.IdDTO(id)));
//    }
//
//    @ApiOperation("店铺轮播图")
//    @GetMapping(value = "/bannerList")
//    public ResponseData<BbcShopVO.DetailVO> bannerList() {
//        return ResponseData.data(bbcShopRpc.bannerList(new BbcShopDTO.IdDTO(id)));
//    }
//
//    @ApiOperation("店铺楼层")
//    @GetMapping(value = "/shopFloor")
//    public ResponseData<BbcShopVO.DetailVO> shopFloor() {
//        return ResponseData.data(bbcShopRpc.shopFloor(new BbcShopDTO.IdDTO(id)));
//    }
}
