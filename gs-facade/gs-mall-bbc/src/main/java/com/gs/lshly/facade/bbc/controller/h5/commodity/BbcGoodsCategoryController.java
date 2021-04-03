package com.gs.lshly.facade.bbc.controller.h5.commodity;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsCategoryVO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsBrandVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
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
@Api(tags = "2C商城商品分类列表管理-v1.1.0")
public class BbcGoodsCategoryController {

    @DubboReference
    private IBbcGoodsCategoryRpc bbcGoodsCategoryRpc;

    @ApiOperation("2C商城商品分类列表")
    @GetMapping("")
    public ResponseData<List<BbcGoodsCategoryVO.CategoryTreeVO>> list() {
        return ResponseData.data(bbcGoodsCategoryRpc.listGoodsCategory());
    }

    @ApiOperation("2C商城商品分类列表-v1.1.0")
    @GetMapping("list/label")
    public ResponseData<List<BbcGoodsCategoryVO.CategoryTreeVO>> listByLabel(BbcGoodsCategoryQTO.ListQTO listQTO) {
        List<BbcGoodsCategoryVO.CategoryTreeVO> listVOS = bbcGoodsCategoryRpc.listGoodsCategory(listQTO);
        return ResponseData.data(listVOS);
    }
    @ApiOperation("2C商城根据产品分类查询品牌列表-v1.1.0")
    @GetMapping("brand/list")
    public ResponseData<PageData<BbcGoodsCategoryVO.CategoryTreeVO>> brandList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {
        PageData<GoodsBrandVO.ListVO> listVOS = bbcGoodsCategoryRpc.brandList(categoryIdQTO);
        return ResponseData.data(listVOS);
    }
    @ApiOperation("2C商城根据产品分类查询商品列表-v1.1.0")
    @GetMapping("goods/list")
    public ResponseData<PageData<GoodsInfoVO.ListVO>> goodsList(GoodsInfoQTO.CategoryIdQTO categoryIdQTO) {
        PageData<GoodsInfoVO.ListVO> listVOS = bbcGoodsCategoryRpc.goodsList(categoryIdQTO);
        return ResponseData.data(listVOS);
    }

}
