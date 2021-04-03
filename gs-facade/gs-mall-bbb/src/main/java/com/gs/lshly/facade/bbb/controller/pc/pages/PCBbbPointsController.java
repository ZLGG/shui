package com.gs.lshly.facade.bbb.controller.pc.pages;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.enums.TerminalEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsCategoryVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteNoticeQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteBannerVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteNoticeVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteTopicVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.common.struct.bbb.pc.trade.vo.PCBbbMarketActivityVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteTopicQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteTopicVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsCategoryRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteBannerRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteNoticeRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteTopicRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IPCBbbFloorRpc;
import com.gs.lshly.rpc.api.bbb.pc.trade.IPCBbbMarketActivityRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteTopicRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月11日 下午3:59:52
 */
@RestController
@RequestMapping("/bbb/points")
@Api(tags = "2Bpc积分商城专栏页-v1.1.0",description = " ")
public class PCBbbPointsController {

    @DubboReference
    private IPCBbbGoodsCategoryRpc categoryRpc;
    
    @DubboReference
    private IBbbSiteAdvertRpc bbbSiteAdvertRpc;

    @DubboReference
    private IPCBbbFloorRpc pCBbbFloorRpc;

    @DubboReference
    private IBbbSiteBannerRpc bbbSiteBannerRpc;


    @DubboReference
    private IBbbSiteNoticeRpc bbbSiteNoticeRpc;
    
    @DubboReference
    private IPCBbbMarketActivityRpc pCBbbMarketActivityRpc;

    @DubboReference
    private IBbbSiteTopicRpc bbbSiteTopicRpc;

    @DubboReference
    private IBbcSiteTopicRpc bbcSiteTopicRpc;
    
    @ApiOperation("扶贫页轮播图")
    @GetMapping("/bannerList")
    public ResponseData<List<BbbSiteBannerVO.ListVO>> bannerList(BbbSiteBannerQTO.QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        return ResponseData.data(bbbSiteBannerRpc.list(qto));
    }

    @ApiOperation("扶贫页楼层与广告图")
    @GetMapping("/floorOrAdvert")
    public ResponseData<PCBbbHomeVO.FloorOrAdvertVO> floorOrAdvert(PCBbbHomeQTO.QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        return ResponseData.data(pCBbbFloorRpc.pageList(qto));
    }


    @ApiOperation("楼层菜单商品查询")
    @GetMapping("/floorMenuGoodsQuery")
    public ResponseData<List<PCBbbHomeVO.PCFloorMenuGoods>> floorMenuGoodsQuery(PCBbbHomeQTO.MenuGoodsQTO qto) {
        return ResponseData.data(pCBbbFloorRpc.floorMenuGoodsQuery(qto));
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
    
    @ApiOperation("秒杀列表-v1.1.0")
    @GetMapping("/flashsaleList")
    public ResponseData<PCBbbMarketActivityVO.FlashsaleVO> listFlashsale() {
        return ResponseData.data(pCBbbMarketActivityRpc.listFlashsale(new BaseDTO()));
    }
    
    @ApiOperation("电信甄选-v1.1.0")
    @GetMapping("/topicList")
    public ResponseData<List<BbbSiteTopicVO.ListVO>> listTopic() {
        return ResponseData.data(bbbSiteTopicRpc.list());
    }
    
    @ApiOperation("2Bpc端首页分类菜单信息-v1.1.0")
    @GetMapping("")
    public ResponseData<PCBbbGoodsCategoryVO.CategoryMenuVO> getCategoryMenuVO(PCBbbGoodsCategoryQTO.QTO qto) {
        qto.setSubject(SubjectEnum.积分商城.getCode());
        qto.setTerminal(TerminalEnum.BBB.getCode());
    	return ResponseData.data(categoryRpc.getCategoryMenuVO(qto));
    }
    
    @ApiOperation("电信甄选/心选好礼/本地生活/精打细算-v1.1.0")
    @GetMapping("/floorGoods")
    public ResponseData<List<BbcSiteTopicVO.CategoryListVO>> floorGoods() {
    	BbcSiteTopicQTO.QTO qto = new BbcSiteTopicQTO.QTO();
    	qto.setTerminal(TerminalEnum.BBB.getCode());
    	qto.setSubject(SubjectEnum.积分商城.getCode());
        return ResponseData.data(bbcSiteTopicRpc.listPointHome(qto));
    }
}
