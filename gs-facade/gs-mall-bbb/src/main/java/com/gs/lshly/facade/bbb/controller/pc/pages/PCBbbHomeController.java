package com.gs.lshly.facade.bbb.controller.pc.pages;

import com.gs.lshly.common.enums.PcH5Enum;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsInfoQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsCategoryVO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.dto.BbbArticleCategoryDTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbArticleCategoryVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.common.struct.common.dto.CommonSiteActiveDTO;
import com.gs.lshly.common.struct.common.vo.CommonSiteActiveVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsCategoryRpc;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbArticleCategoryRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteVideoRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IPCBbbFloorRpc;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.common.ICommonSiteActiveRpc;
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
@RequestMapping("/bbb/home")
@Api(tags = "2Bpc端首页信息管理",description = " ")
public class PCBbbHomeController {

    @DubboReference
    private IPCBbbGoodsCategoryRpc categoryRpc;

    @DubboReference
    private IPCBbbGoodsInfoRpc goodsInfoRpc;

    @DubboReference
    private IPCBbbFloorRpc pCBbbFloorRpc;

    @DubboReference
    private IBbbShopRpc bbbShopRpc;

    @DubboReference
    private IBbbArticleCategoryRpc bbbArticleCategoryRpc;

    @DubboReference
    private IBbbSiteVideoRpc  bbbSiteVideoRpc;

    @DubboReference
    private ICommonSiteActiveRpc siteActiveRpc;


    @ApiOperation("2Bpc端首页分类菜单信息")
    @GetMapping("")
    public ResponseData<PCBbbGoodsCategoryVO.CategoryMenuVO> getCategoryMenuVO(PCBbbGoodsCategoryQTO.QTO qto) {
        return ResponseData.data(categoryRpc.getCategoryMenuVO(qto));
    }


    @ApiOperation("2Bpc端首页推荐商品信息")
    @GetMapping("/getGoodsRecommendVO")
    public ResponseData<PCBbbGoodsInfoVO.GoodsRecommendVO> getGoodsRecommendVO(PCBbbGoodsInfoQTO.QTO qto) {
        return ResponseData.data(goodsInfoRpc.getRecommendGoodsList(qto));
    }

    @ApiOperation("分类导航搜索管理")
    @GetMapping("/getCategoryNavigationVO")
    public ResponseData<PCBbbGoodsCategoryVO.CategoryNavigationVO> getCategoryNavigationVO(PCBbbGoodsCategoryQTO.CategoryNavigationQTO qto) {
        return ResponseData.data(categoryRpc.getCategoryNavigationVO(qto));
    }

    @ApiOperation("楼层与广告图")
    @GetMapping("/floorOrAdvert")
    public ResponseData<PCBbbHomeVO.FloorOrAdvertVO> floorOrAdvert(PCBbbHomeQTO.QTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        return ResponseData.data(pCBbbFloorRpc.pageList(qto));
    }

    @ApiOperation("楼层菜单商品查询")
    @GetMapping("/floorMenuGoodsQuery")
    public ResponseData<List<PCBbbHomeVO.PCFloorMenuGoods>> floorMenuGoodsQuery(PCBbbHomeQTO.MenuGoodsQTO qto) {
        return ResponseData.data(pCBbbFloorRpc.floorMenuGoodsQuery(qto));
    }

    @ApiOperation("首页店铺搜索")
    @GetMapping("/shopSearch")
    public ResponseData<PageData<PCBbbHomeVO.ShopSearchInfo>> shopSearch(PCBbbHomeQTO.ShopSearchQTO qto) {
        return ResponseData.data(bbbShopRpc.shopSearchList(qto));
    }

    @ApiOperation("首页底部文章和链接列表")
    @GetMapping("/articleLinks")
    public ResponseData<BbbArticleCategoryVO.ArticleLinksVO> articleLinks() {
        return ResponseData.data(bbbArticleCategoryRpc.homeIndexArticleLinks(new BaseDTO()));
    }

    @ApiOperation("首页底部文章详情")
    @GetMapping("/detailsArticle/{id}")
    public ResponseData<BbbArticleCategoryVO.DetailsVO> detailsArticle(@PathVariable String id) {
        return ResponseData.data(bbbArticleCategoryRpc.details(new BbbArticleCategoryDTO.IdDTO(id)));
    }


    @ApiOperation("首页视频")
    @GetMapping("/video")
    public ResponseData<BbbSiteVideoVO.ListVO> video(BbbSiteVideoQTO.QTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        return ResponseData.data(bbbSiteVideoRpc.list(qto));
    }

    @ApiOperation("活动图片配置")
    @GetMapping("/getSiteActiveVO")
    public ResponseData<CommonSiteActiveVO.ListVO> getSiteActiveVO(CommonSiteActiveDTO.QueryDTO dto) {
        dto.setPcShow(PcH5Enum.NO.getCode());
        dto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(siteActiveRpc.getCommonSiteActiveVO(dto));
    }

}
