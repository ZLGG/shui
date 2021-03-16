package com.gs.lshly.facade.bbc.controller.pc.pages;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteBannerVO;
import com.gs.lshly.common.struct.bbb.pc.pages.qto.PCBbbHomeQTO;
import com.gs.lshly.common.struct.bbb.pc.pages.vo.PCBbbHomeVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteBannerRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IPCBbbFloorRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Starry
 * @Date 17:27 2020/11/25
 */
@RestController
@RequestMapping("/bbc/pc/welfare")
@Api(tags = "2C PC扶贫专栏页")
public class BbcPcWelfareController {

    @DubboReference
    private IBbbSiteAdvertRpc bbbSiteAdvertRpc;

    @DubboReference
    private IPCBbbFloorRpc pCBbbFloorRpc;

    @DubboReference
    private IBbbSiteBannerRpc bbbSiteBannerRpc;




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



}
