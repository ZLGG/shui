package com.gs.lshly.facade.bbc.controller.h5.foundation;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteNavigationQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteNavigationVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteNavigationRpc;
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
@RequestMapping("/bbc/siteNavigation")
@Api(tags = "站点导航管理")
public class BbcSiteNavigationController {

    @DubboReference
    private IBbcSiteNavigationRpc bbcSiteNavigationRpc;

    @ApiOperation("站点导航列表")
    @GetMapping("navigationList")
    public ResponseData<List<BbcSiteNavigationVO.ListVO>> list(BbcSiteNavigationQTO.QTO qto) {
        return ResponseData.data(bbcSiteNavigationRpc.list(qto));
    }


}
