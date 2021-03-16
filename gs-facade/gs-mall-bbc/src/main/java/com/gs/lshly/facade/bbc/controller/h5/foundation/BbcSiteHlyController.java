package com.gs.lshly.facade.bbc.controller.h5.foundation;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteFloorVO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteVideoRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Starry
 * @Date 17:27 2020/11/25
 */
@RestController
@RequestMapping("/bbc/hly")
@Api(tags = "b2c好粮油专栏页")
public class BbcSiteHlyController {

    @DubboReference
    private IBbcSiteVideoRpc bbcSiteVideoRpc;

    @DubboReference
    private IBbcSiteAdvertRpc bbcSiteAdvertRpc;

    @ApiOperation("好粮油视频")
    @GetMapping("/video")
    public ResponseData<BbcSiteVideoVO.ListVO> video(BbcSiteVideoQTO.QTO qto) {
        qto.setSubject(SubjectEnum.好粮油.getCode());
        return ResponseData.data(bbcSiteVideoRpc.video(qto));
    }

    @ApiOperation("好粮油广告图")
    @GetMapping("/pageAdvert")
    public ResponseData<PageData<BbcSiteFloorVO.ListVO>> pageAdvert(BbcSiteAdvertQTO.SubjectPageQTO qto) {
        qto.setSubject(SubjectEnum.好粮油.getCode());
        return ResponseData.data(bbcSiteAdvertRpc.subjectAdvertPageList(qto));
    }

}
