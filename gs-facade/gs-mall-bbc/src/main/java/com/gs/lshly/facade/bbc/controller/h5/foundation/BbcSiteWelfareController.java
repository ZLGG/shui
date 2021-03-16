package com.gs.lshly.facade.bbc.controller.h5.foundation;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBannerRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteFloorRpc;
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
@RequestMapping("/bbc/welfare")
@Api(tags = "b2c扶贫专栏页")
public class BbcSiteWelfareController {

    @DubboReference
    private IBbcSiteBannerRpc bbcSiteBannerRpc;

    @DubboReference
    private IBbcSiteFloorRpc bbcSiteFloorRpc;

    @ApiOperation("扶贫页轮播图")
    @GetMapping("/bannerList")
    public ResponseData<List<BbcSiteBannerVO.ListVO>> bannerList(BbcSiteBannerQTO.QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        return ResponseData.data(bbcSiteBannerRpc.list(qto));
    }

    @ApiOperation("扶贫页楼层商品-不再维护")
    @GetMapping("/floorGoods")
    public ResponseData<List<BbcSiteFloorVO.ListVO>> floorGoods(BbcSiteFloorQTO.QTO qto) {
        qto.setSubject(SubjectEnum.扶贫.getCode());
        return ResponseData.data(bbcSiteFloorRpc.list(qto));
    }

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

}
