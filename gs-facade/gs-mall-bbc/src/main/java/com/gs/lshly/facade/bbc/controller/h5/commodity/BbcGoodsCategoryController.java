package com.gs.lshly.facade.bbc.controller.h5.commodity;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.rpc.api.bbc.commodity.IBbcGoodsCategoryRpc;
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
* @author Starry
* @since 2020-10-23
*/
@RestController
@RequestMapping("/bbc/goodsCategory")
@Api(tags = "2C商城商品分类列表管理")
public class BbcGoodsCategoryController {

    @DubboReference
    private IBbcGoodsCategoryRpc bbcGoodsCategoryRpc;

    @ApiOperation("2C商城商品分类列表")
    @GetMapping("")
    public ResponseData<List<BbcGoodsCategoryVO.CategoryTreeVO>> list() {
        return ResponseData.data(bbcGoodsCategoryRpc.listGoodsCategory());
    }

}
