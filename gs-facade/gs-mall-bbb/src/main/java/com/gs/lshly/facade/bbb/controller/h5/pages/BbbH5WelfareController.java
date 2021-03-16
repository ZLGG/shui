package com.gs.lshly.facade.bbb.controller.h5.pages;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.h5.pages.qto.BbbH5WelfareQTO;
import com.gs.lshly.common.struct.bbb.h5.pages.vo.BbbH5WelfareVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5ComplexWelfareRpc;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteBannerRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IPCBbbFloorRpc;
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
@RequestMapping("/bbb/h5/welfare")
@Api(tags = "2Bh5扶贫专栏页",description = " ")
public class BbbH5WelfareController {

    @DubboReference
    private IBbbH5ComplexWelfareRpc bbbH5ComplexWelfareRpc;

    @ApiOperation("扶贫专栏组合数据")
    @GetMapping("/topComplex")
    public ResponseData<BbbH5WelfareVO.TopComplexVO> bannerList(BbbH5WelfareQTO.QTO qto) {
        return ResponseData.data(bbbH5ComplexWelfareRpc.topComplex(qto));
    }

    @ApiOperation("楼层商品查询(最大商品数量pageNum=1,pageSize = 最大数量)")
    @GetMapping("/floorGoodsQuery/{floorId}")
    public ResponseData<PageData<BbbH5GoodsInfoVO.HomeInnerServiceVO>> floorGoodsQuery(@PathVariable String floorId, BbbH5WelfareQTO.FloorGoodsQTO qto) {
        qto.setFloorId(floorId);
        return ResponseData.data(bbbH5ComplexWelfareRpc.floorGoodsQuery(qto));
    }

}
