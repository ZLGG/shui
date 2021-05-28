package com.gs.lshly.facade.bbc.controller.test;


import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import com.gs.lshly.rpc.api.test.IESTestRpc;
import com.gs.lshly.rpc.api.test.IMQTestRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author lxus
* @since 2020-10-16
*/
@RestController
@RequestMapping("/test/es")
@Api(tags = "搜索引擎测试")
public class ElasticSearchTestController {

    @DubboReference
    private IESTestRpc iesTestRpc;

    @ApiOperation("导入商品到es")
    @GetMapping("/import/{goodsId}")
    public ResponseData<Void> sendMsg(@PathVariable String goodsId) {
        iesTestRpc.importGoodsInfoById(goodsId);
        return ResponseData.success("导入成功");
    }

    @ApiOperation("根据商品标题或名称搜索")
    @GetMapping("/search")
    public ResponseData<Void> queryMsg(String nameOrTitle) {
        List<BbcGoodsInfoVO.GoodsListVO> result = iesTestRpc.searchByNameOrTitle(nameOrTitle, nameOrTitle);
        return ResponseData.data(result);
    }

}
