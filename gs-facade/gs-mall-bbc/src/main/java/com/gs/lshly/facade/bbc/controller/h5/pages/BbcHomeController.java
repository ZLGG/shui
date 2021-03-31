package com.gs.lshly.facade.bbc.controller.h5.pages;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbArticleCategoryVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsCategoryRpc;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbArticleCategoryRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteNoticeRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteVideoRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IPCBbbFloorRpc;
import com.gs.lshly.rpc.api.bbb.pc.merchant.IBbbShopRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteTopicRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午3:52:17
 */
@RestController
@RequestMapping("/bbc/home")
@Api(tags = "2CH5端首页信息管理-v1.1.0")
public class BbcHomeController {

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
    private IBbcSiteTopicRpc bbcSiteTopicRpc; 
    
    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
    @DubboReference
    private IBbbSiteNoticeRpc bbbSiteNoticeRpc;


    @ApiOperation("分类/banner/菜单信息-v1.1.0")
    @GetMapping("")
    public ResponseData<PCBbbGoodsCategoryVO.CategoryMenuVO> getCategoryMenuVO(PCBbbGoodsCategoryQTO.QTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
    	return ResponseData.data(categoryRpc.getCategoryMenuVO(qto));
    }

    @ApiOperation("电信/秒杀专区-v1.1.0")
    @GetMapping("/floorGoods")
    public ResponseData<List<BbcSiteTopicVO.CategoryListVO>> floorGoods(BbcSiteTopicQTO.QTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
    	return ResponseData.data(bbcSiteTopicRpc.list(qto));
    }
    
    
    @ApiOperation("猜你喜欢-v1.1.0")
    @GetMapping("/enjoyList")
    public ResponseData<PageData<GoodsInfoVO.DetailVO>> listEnjoy(BbcSiteTopicQTO.EnjoyQTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
    	return ResponseData.data(bbcSiteTopicRpc.pageEnjoy(qto));
    }

    @ApiOperation("公告列表-v1.1.0")
    @GetMapping("/noticeList")
    public ResponseData<List<BbbSiteNoticeVO.ListVO>> listNotice() {
    	BbbSiteNoticeQTO.QTO qto = new BbbSiteNoticeQTO.QTO();
    	qto.setPageNum(1);
    	qto.setPageNum(10);
        return ResponseData.data(bbbSiteNoticeRpc.list(qto));
    }
    
    @ApiOperation("公告详情-v1.1.0")
    @GetMapping("/noticeDetail")
    public ResponseData<BbbSiteNoticeVO.DetailVO> detailNotice(BbbSiteNoticeQTO.IDQTO qto) {
        return ResponseData.data(bbbSiteNoticeRpc.detailNotice(qto));
    }
    
    @ApiOperation("2C PC端首页推荐商品信息")
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

    @ApiOperation("电信甄选专区产品列表-v1.1.0")
    @GetMapping("/topicGoods")
    public ResponseData<BbcSiteTopicVO.CategoryListVO> topicGoods(String topicId) {
        return ResponseData.data(bbcSiteTopicRpc.topicGoods(topicId));
    }
    
    @ApiOperation("IN会员产品专区-v1.1.0")
    @GetMapping("/inMemberGoods")
    public ResponseData<BbcGoodsInfoVO> inMemberGoods(BbcGoodsInfoQTO.InMemberGoodsQTO qto) {
        return ResponseData.data(bbcGoodsInfoRpc.pageInMemberGoods(qto));
    }
}
