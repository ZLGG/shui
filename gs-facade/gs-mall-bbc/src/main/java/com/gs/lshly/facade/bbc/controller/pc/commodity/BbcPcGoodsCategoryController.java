package com.gs.lshly.facade.bbc.controller.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsCategoryVO;
import com.gs.lshly.rpc.api.bbb.pc.commodity.IPCBbbGoodsCategoryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Starry
 * @Date 13:58 2020/11/30
 */
@RestController
@RequestMapping("/bbc/pc/goodsCategory")
@Api(tags = "2C PC端类目信息管理")
public class BbcPcGoodsCategoryController {

    @DubboReference
    private IPCBbbGoodsCategoryRpc categoryRpc;

    @ApiOperation("分类导航搜索管理")
    @PostMapping("/getCategoryNavigationVO")
    public ResponseData<PCBbbGoodsCategoryVO.CategoryNavigationVO> getCategoryNavigationVO(@RequestBody PCBbbGoodsCategoryQTO.CategoryNavigationQTO qto) {
        return ResponseData.data(categoryRpc.getCategoryNavigationVO(qto));
    }
}
