package com.gs.lshly.facade.bbc.controller.h5.foundation;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBannerQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBannerVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBannerRpc;
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
* @since 2020-11-04
*/
@RestController
@RequestMapping("/bbc/siteBanner")
@Api(tags = "站点轮播图管理")
public class BbcSiteBannerController {

    @DubboReference
    private IBbcSiteBannerRpc bbcSiteBannerRpc;

    @ApiOperation("站点轮播图列表")
    @GetMapping("/bannerList")
    public ResponseData<List<BbcSiteBannerVO.ListVO>> list(BbcSiteBannerQTO.QTO qto) {
        return ResponseData.data(bbcSiteBannerRpc.list(qto));
    }



}
