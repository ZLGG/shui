package com.gs.lshly.facade.bbb.controller.h5.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsCategoryVO;
import com.gs.lshly.rpc.api.bbb.h5.commodity.IBbbH5GoodsCategoryRpc;
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
* @author Starry
* @since 2020-10-23
*/
@RestController
@RequestMapping("/bbb/h5/goodsCategory")
@Api(tags = "2B商城商品分类")
public class BbbH5GoodsCategoryController {

    @DubboReference
    private IBbbH5GoodsCategoryRpc bbcGoodsCategoryRpc;

    @ApiOperation("2B商城商品分类列表")
    @GetMapping("")
    public ResponseData<List<BbbH5GoodsCategoryVO.CategoryTreeVO>> list(BaseDTO dto) {
        return ResponseData.data(bbcGoodsCategoryRpc.listGoodsCategory(dto));
    }

}
