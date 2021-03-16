package com.gs.lshly.facade.bbc.controller.h5.foundation;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.foundation.qto.BbcSiteBroadQTO;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteBroadVO;
import com.gs.lshly.rpc.api.bbc.foundation.IBbcSiteBroadRpc;
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
@RequestMapping("/bbc/siteBroad")
@Api(tags = "站点公告文章管理")
public class BbcSiteBroadController {

    @DubboReference
    private IBbcSiteBroadRpc bbcSiteBroadRpc;

    @ApiOperation("公告文章列表")
    @GetMapping("")
    public ResponseData<List<BbcSiteBroadVO.ListVO>> list(BbcSiteBroadQTO.QTO qto) {
        return ResponseData.data(bbcSiteBroadRpc.list(qto));
    }

//    @ApiOperation("公告文章详情")
//    @GetMapping("/{id}")
//    public ResponseData<BbcSiteBroadVO.DetailsVO> details(@PathVariable String id) {
//        BbcSiteBroadDTO.IdDTO dto = new BbcSiteBroadDTO.IdDTO(id);
//        return ResponseData.data(bbcSiteBroadRpc.details(dto));
//    }

}
