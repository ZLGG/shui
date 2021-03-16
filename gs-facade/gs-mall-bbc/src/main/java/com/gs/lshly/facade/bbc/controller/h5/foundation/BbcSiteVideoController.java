package com.gs.lshly.facade.bbc.controller.h5.foundation;

import com.gs.lshly.common.enums.SubjectEnum;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteVideoQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteVideoVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteVideoRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/bbc/siteVideo")
@Api(tags = "站点宣传视频管理")
public class BbcSiteVideoController {

    @DubboReference
    private IBbcSiteVideoRpc bbcsiteVideoRpc;

    @ApiOperation("宣传视频列表")
    @GetMapping("")
    public ResponseData<List<BbcSiteVideoVO.ListVO>> list(BbcSiteVideoQTO.QTO qto) {
        qto.setSubject(SubjectEnum.默认.getCode());
        return ResponseData.data(bbcsiteVideoRpc.list(qto));
    }


}
