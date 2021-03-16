package com.gs.lshly.facade.bbb.controller.h5.pages;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.*;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.*;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.*;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteBannerRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteVideoRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.common.utils.Page;
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
@RequestMapping("/bbb/h5/home")
@Api(tags = "B2B首页数据",description = " ")
public class BbbH5HomeController {

    @DubboReference
    private IBbbH5ComplexHomeRpc bbbH5ComplexHomeRpc;

    @ApiOperation("首页组合数据")
    @GetMapping("/complex")
    public ResponseData<BbbH5ComplexHomeVO.TopComplexVO> topComplex(BbbH5ComplexHomeQTO.QTO qto) {

        return ResponseData.data(bbbH5ComplexHomeRpc.topComplex(qto));
    }


    @ApiOperation("首页楼层商品查询,(最大商品数量pageNum=1,pageSize = 最大数量)")
    @GetMapping("/floorGoods/{floorId}")
    public ResponseData<PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO>> floorGoodsList(@PathVariable String floorId, BbbH5SiteFloorQTO.GoodsListQTO qto) {
        qto.setFloorId(floorId);
        return ResponseData.data(bbbH5ComplexHomeRpc.goodsList(qto));
    }

}
