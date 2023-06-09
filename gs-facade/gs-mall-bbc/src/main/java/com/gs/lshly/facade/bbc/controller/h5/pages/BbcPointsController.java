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
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsInfoQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcPointHomeQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcPointHomePageVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSitePopupVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.common.struct.platadmin.foundation.qto.SiteAdvertPopupQTO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsCategoryRpc;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsInfoRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcPointHomeRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBannerRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteFloorRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSitePopupRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteTopicRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月12日 下午1:59:54
 */
@RestController
@RequestMapping("/bbc/points")
@Api(tags = "b2c积分商城专栏页-v1.1.0")
@SuppressWarnings("unchecked")
public class BbcPointsController {

	@DubboReference
    private IBbcSiteBannerRpc bbcSiteBannerRpc;

    @DubboReference
    private IBbcSiteFloorRpc bbcSiteFloorRpc;

    @DubboReference
    private IPCBbbGoodsCategoryRpc categoryRpc;
    
    @DubboReference
    private IBbcSiteTopicRpc bbcSiteTopicRpc;
    
    @DubboReference
    private IBbcSitePopupRpc bbcSitePopupRpc;
    
    @DubboReference
    private IBbcPointHomeRpc bbcPointHomeRpc;
    
    @DubboReference
    private IBbcGoodsInfoRpc bbcGoodsInfoRpc;
    
    @ApiOperation("/分类/banner/菜单信息/电信产品/秒杀专区/电信国际/IN会员专区/心选好礼/本地生活/精打细算-v1.1.0")
    @GetMapping("")
    public ResponseData<List<BbcPointHomePageVO.ListVO>> home(BbcPointHomeQTO.QTO qto) {
        qto.setSubject(SubjectEnum.积分商城.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
    	return ResponseData.data(bbcPointHomeRpc.getHome(qto));
    }
    
	@ApiOperation("电信产品列表-v1.1.0")
    @GetMapping("/ctccProduct/goodsinfo")
    public ResponseData<BbcSiteTopicVO.CategoryListVO> listCtccProductGoodsInfo(String id) {
        return ResponseData.data(bbcSiteTopicRpc.listGoodsInfo(id));
    }
    
//    @ApiOperation("电信国际首页-v1.1.0")
//    @GetMapping("/ctccInternational")
//    public ResponseData<List<BbcPointHomePageVO.ListVO>> ctccInternationalHome(BbcPointHomeQTO.CtccInternationalQTO qto) {
//        qto.setSubject(SubjectEnum.电信国际.getCode());
//        qto.setTerminal(TerminalEnum.BBC.getCode());
//    	return ResponseData.data(bbcPointHomeRpc.ctccInternationalHome(qto));
//    }
    
//    @ApiOperation("秒杀首页-v1.1.0")
//    @GetMapping("/seckill")
//    public ResponseData<List<BbcPointHomePageVO.ListVO>> seckillHome(BbcPointHomeQTO.SeckillQTO qto) {
//        qto.setSubject(SubjectEnum.积分商城.getCode());
//        qto.setTerminal(TerminalEnum.BBC.getCode());
//    	return ResponseData.data(bbcPointHomeRpc.seckillHome(qto));
//    }
    
    @ApiOperation("电信甄选/心选好礼/本地生活/精打细算/名品集市产品列表-v1.1.0")
    @GetMapping("/floorGoods")
    public ResponseData<List<BbcSiteTopicVO.CategoryListVO>> floorGoods() {
    	BbcSiteTopicQTO.QTO qto = new BbcSiteTopicQTO.QTO();
    	qto.setTerminal(TerminalEnum.BBC.getCode());
    	qto.setSubject(SubjectEnum.积分商城.getCode());
        return ResponseData.data(bbcSiteTopicRpc.listPointHome(qto));
    }
    
    @ApiOperation("电信甄选-查看更多-v1.1.0")
    @GetMapping("/floorGoodsSearchmore")
    public ResponseData<BbcSiteTopicVO.GoodsVO> floorGoodsSearchmore(BbcSiteTopicQTO.SearchmoreQTO qto) {
        qto.setSubject(SubjectEnum.积分商城.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        BbcSiteTopicVO.GoodsVO goodsVO= bbcSiteTopicRpc.pageMore(qto);
    	return ResponseData.data(goodsVO);
    }
    
    @ApiOperation("猜你喜欢-v1.1.0")
    @GetMapping("/enjoyList")
    public ResponseData<PageData<GoodsInfoVO.DetailVO>> listEnjoy(BbcSiteTopicQTO.EnjoyQTO qto) {
//        qto.setSubject(SubjectEnum.积分商城.getCode());
//        qto.setTerminal(TerminalEnum.BBC.getCode());
        BbcGoodsInfoQTO.GoodsSearchListQTO qto1 = new BbcGoodsInfoQTO.GoodsSearchListQTO();
        return ResponseData.data(bbcGoodsInfoRpc.pageGoodsData(qto1));
        
//    	return ResponseData.data(bbcSiteTopicRpc.pageEnjoy(qto));
    }
    
    @ApiOperation("专区产品列表-v1.1.0")
    @GetMapping("/topicGoods")
    public ResponseData<BbcSiteTopicVO.CategoryListVO> topicGoods(String topicId) {
        return ResponseData.data(bbcSiteTopicRpc.topicGoods(topicId));
    }
    
    @ApiOperation("查询广告弹窗-v1.1.0")
    @GetMapping("/advertPopup")
    public ResponseData<BbcSitePopupVO.DetailVO> advertPopup() {
    	SiteAdvertPopupQTO.BBBPCQTO qto = new SiteAdvertPopupQTO.BBBPCQTO();
        qto.setSubject(SubjectEnum.积分商城.getCode());
        qto.setTerminal(TerminalEnum.BBC.getCode());
        return ResponseData.data(bbcSitePopupRpc.getPopup(qto));
    }
    
    @ApiOperation("扶贫页轮播图")
    @GetMapping("/bannerList")
    public ResponseData<List<BbcSiteBannerVO.ListVO>> bannerList(BbcSiteBannerQTO.QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        return ResponseData.data(bbcSiteBannerRpc.list(qto));
    }

    /**
    @ApiOperation("扶贫页楼层商品-不再维护")
    @GetMapping("/floorGoods")
    public ResponseData<List<BbcSiteFloorVO.ListVO>> floorGoods(BbcSiteFloorQTO.QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        return ResponseData.data(bbcSiteFloorRpc.list(qto));
    }
	**/
    @ApiOperation("站点楼层2.0")
    @GetMapping("/floorList")
    public ResponseData<List<BbcSiteFloorVO.List2VO>> floorList(BbcSiteFloorQTO.QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        return ResponseData.data(bbcSiteFloorRpc.list2(qto));
    }

    @ApiOperation("站点楼层商品(最大商品数量pageNum=1,pageSize = 最大数量)")
    @GetMapping("/floorGoods/{floorId}")
    public ResponseData<PageData<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>> goodsMore(@PathVariable String floorId, BbcSiteFloorQTO.GoodsMoreQTO qto) {
        qto.setFloorId(floorId);
        return ResponseData.data(bbcSiteFloorRpc.goodsMore(qto));
    }
    
    @ApiOperation("我能兑换-v1.1.0")
    @GetMapping("/myIntegrationExchange")
    public ResponseData<List<BbcGoodsInfoVO.MyIntegrationExchangeVO>> myIntegrationExchange() {
        return ResponseData.data(bbcGoodsInfoRpc.myIntegrationExchange());
    }

}
