package com.gs.lshly.facade.bbc.controller.h5.foundation;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteAdvertQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteAdvertRpc;
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
* @author hyy
* @since 2020-11-03
*/
@RestController
@RequestMapping("/bbc/siteAdvert")
@Api(tags = "站点广告图管理")
public class BbcSiteAdvertController {

    @DubboReference
    private IBbcSiteAdvertRpc bbcSiteAdvertRpc;

    @ApiOperation("专栏广告图列表")
    @GetMapping("/subjectAdvertList")
    public ResponseData<List<BbcSiteAdvertVO.SubjectListVO>> subjectAdvertList(BbcSiteAdvertQTO.SubjectQTO qto) {
        return ResponseData.data(bbcSiteAdvertRpc.subjectAdvertList(qto));
    }

}
