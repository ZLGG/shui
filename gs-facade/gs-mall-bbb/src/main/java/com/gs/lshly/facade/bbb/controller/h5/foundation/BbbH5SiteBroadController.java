package com.gs.lshly.facade.bbb.controller.h5.foundation;

import java.util.List;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.h5.foundation.qto.BbbH5SiteBroadQTO;
import com.gs.lshly.common.struct.bbb.h5.foundation.vo.BbbH5SiteBroadVO;
import com.gs.lshly.rpc.api.bbb.h5.foundation.IBbbH5SiteBroadRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
* <p>
*  前端控制器
* </p>
*
* @author hyy
* @since 2020-11-03
*/
@RestController
@RequestMapping("/bbb/h5/siteBroad")
@Api(tags = "站点公告提示管理")
public class BbbH5SiteBroadController {

    @DubboReference
    private IBbbH5SiteBroadRpc bbbH5SiteBroadRpc;

    @ApiOperation("公告提示列表")
    @GetMapping("")
    public ResponseData<List<BbbH5SiteBroadVO.ListVO>> list(BbbH5SiteBroadQTO.QTO qto) {
        return ResponseData.data(bbbH5SiteBroadRpc.list(qto));
    }

}
