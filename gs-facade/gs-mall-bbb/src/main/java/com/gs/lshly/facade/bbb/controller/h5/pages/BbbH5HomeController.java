package com.gs.lshly.facade.bbb.controller.h5.pages;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5ComplexHomeQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteFloorQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5ComplexHomeVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5ComplexHomeRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
