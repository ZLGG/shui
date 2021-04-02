package com.gs.lshly.facade.bbb.controller.pc.pages;

import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.merchant.dto.BbbShopDTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbShopHomeVO;
import com.gs.lshly.common.struct.bbc.merchant.vo.BbcShopVO;
import com.gs.lshly.common.struct.common.dto.CommonMerchantArticleDTO;
import com.gs.lshly.common.struct.common.qto.CommonMerchantArticleQTO;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.common.ICommonMerchantArticleRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @Author Starry
 * @Date 17:27 2020/11/25
 */
@RestController
@RequestMapping("/bbb/shopHome")
@Api(tags = "2Bpc端店铺首页管理",description = " ")
public class PCBbbShopHomeController {

    @DubboReference
    private IBbbShopRpc bbbShopRpc;
    @DubboReference
    private ICommonMerchantArticleRpc commonMerchantArticleRpc;

    @ApiOperation("2Bpc端店铺首页店铺信息 + 店铺自定分类 + 通栏广告图 + 广告图 + 顶部菜单")
    @GetMapping("/{id}")
    public ResponseData<PCBbbShopHomeVO.ShopHomeVO> list(@PathVariable String id) {
        return ResponseData.data(bbbShopRpc.index(new BbbShopDTO.IdDTO(id)));
    }

    @ApiOperation("2Bpc店铺楼层商品(不推荐使用，弃用)")
    @GetMapping("/floorGoods/{id}")
    public ResponseData<List<PCBbbShopHomeVO.FloorVO>> floorGoods(@PathVariable String id) {
        return ResponseData.data(bbbShopRpc.floorFirstList(new BbbShopDTO.IdDTO(id)));
    }

    @ApiOperation("2Bpc店铺楼层列表")
    @GetMapping("/floorList/{id}")
    public ResponseData<List<PCBbbShopHomeVO.FloorVO>> floorList(@PathVariable String id) {
        return ResponseData.data(bbbShopRpc.floorList(new BbbShopDTO.IdDTO(id)));
    }

    @ApiOperation("2Bpc店铺楼层商品列表")
    @GetMapping("/floorGoodsList/{floorId}")
    public ResponseData<PCBbbShopHomeVO.FloorGoodsListVO> floorGoodsList(@PathVariable String floorId) {
        return ResponseData.data(bbbShopRpc.floorGoodsList(new BbbShopDTO.FloorIdDTO(floorId)));
    }

    @ApiOperation("店铺客服")
    @GetMapping("/service/{id}")
    public ResponseData<PCBbbShopHomeVO.ShopServiceVO> service(@PathVariable String id) {
        return ResponseData.data(bbbShopRpc.shopService(new BbbShopDTO.IdDTO(id)));
    }


    @ApiOperation("商家文章列表")
    @GetMapping(value = "/getMerchantArticle/{shopId}")
    public ResponseData<BbcShopVO.ShopIdName> getMerchantArticle(@PathVariable String shopId, CommonMerchantArticleQTO.QTO qto) {
        qto.setShopId(shopId);
        qto.setPcShow(PcH5Enum.NO.getCode());
        return ResponseData.data(commonMerchantArticleRpc.pageMerchantArticleVO(qto));
    }

    @ApiOperation("商家文章详情")
    @GetMapping(value = "/getMerchantArticleDetail/{articleId}")
    public ResponseData<BbcShopVO.ShopIdName> getMerchantArticleDetail(@PathVariable String articleId) {
        return ResponseData.data(commonMerchantArticleRpc.detailMerchantArticle(new CommonMerchantArticleDTO.IdDTO(articleId)));
    }

}
