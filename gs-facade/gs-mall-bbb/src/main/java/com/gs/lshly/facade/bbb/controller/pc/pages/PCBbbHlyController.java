package com.gs.lshly.facade.bbb.controller.pc.pages;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteAdvertQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteBannerQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.qto.BbbSiteVideoQTO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteAdvertVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteBannerVO;
import com.gs.lshly.common.struct.bbb.pc.foundation.vo.BbbSiteVideoVO;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteBannerRpc;
import com.gs.lshly.rpc.api.bbb.pc.foundation.IBbbSiteVideoRpc;
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
@RequestMapping("/bbb/hly")
@Api(tags = "2Bpc好粮油专栏页",description = " ")
public class PCBbbHlyController {

    @DubboReference
    private IBbbSiteAdvertRpc bbbSiteAdvertRpc;

    @DubboReference
    private IBbbSiteVideoRpc bbbSiteVideoRpc;

    @DubboReference
    private IBbbSiteBannerRpc bbbSiteBannerRpc;

    @ApiOperation("好粮油轮播图")
    @GetMapping("/bannerList")
    public ResponseData<List<BbbSiteBannerVO.ListVO>> bannerList(BbbSiteBannerQTO.QTO qto) {
        qto.setSubject(SubjectEnum.好粮油.getCode());
        return ResponseData.data(bbbSiteBannerRpc.list(qto));
    }

    @ApiOperation("好粮油广告图")
    @GetMapping("/advertList")
    public ResponseData<PageData<BbbSiteAdvertVO.SubjectListVO>> advertPageList(BbbSiteAdvertQTO.QTO qto) {
        qto.setSubject(SubjectEnum.好粮油.getCode());
        return ResponseData.data(bbbSiteAdvertRpc.pageList(qto));
    }

    @ApiOperation("好粮油页视频")
    @GetMapping("/video")
    public ResponseData<BbbSiteVideoVO.ListVO> video(BbbSiteVideoQTO.QTO qto) {
        qto.setSubject(SubjectEnum.好粮油.getCode());
        return ResponseData.data(bbbSiteVideoRpc.list(qto));
    }

}
