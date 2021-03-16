package com.gs.lshly.facade.bbc.controller.h5.foundation;
import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteFloorQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteFloorRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author xxfc
* @since 2020-11-02
*/
@RestController
@RequestMapping("/bbc/siteFloor")
@Api(tags = "站点楼层管理")
public class BbcSiteFloorController {

    @DubboReference
    private IBbcSiteFloorRpc bbcSiteFloorRpc;

    @ApiOperation("站点楼层数据-(不在维护)")
    @GetMapping("")
    public ResponseData<List<BbcSiteFloorVO.ListVO>> list(BbcSiteFloorQTO.QTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        return ResponseData.data(bbcSiteFloorRpc.list(qto));
    }

    @ApiOperation("站点楼层2.0")
    @GetMapping("/floorList")
    public ResponseData<List<BbcSiteFloorVO.List2VO>> floorList(BbcSiteFloorQTO.QTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        return ResponseData.data(bbcSiteFloorRpc.list2(qto));
    }

    @ApiOperation("站点楼层商品(最大商品数量pageNum=1,pageSize = 最大数量)")
    @GetMapping("/floorGoods/{floorId}")
    public ResponseData<PageData<BbcGoodsInfoVO.HomeAndShopInnerServiceVO>> goodsMore(@PathVariable String floorId,BbcSiteFloorQTO.GoodsMoreQTO qto) {
        qto.setFloorId(floorId);
        return ResponseData.data(bbcSiteFloorRpc.goodsMore(qto));
    }
}
