package com.gs.lshly.facade.merchant.controller.pc.commodity;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsCategoryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsCategoryVO;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsCategoryRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchGoodsShopNavigationRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Starry
 * @Date 16:49 2020/10/19
 */
@RestController
@RequestMapping("/merchant/pc/goodsCategory")
@Api(tags = "商品类目管理")
public class PCMerchGoodsCategoryController {
    @DubboReference
    private IPCMerchAdminGoodsCategoryRpc categoryRpc;

    @DubboReference
    private IPCMerchGoodsShopNavigationRpc shopNavigationRpc;

    @ApiOperation("与店铺关联的类目信息列表")
    @GetMapping("")
    public ResponseData<List<PCMerchGoodsCategoryVO.CategoryTreeVO>> list(PCMerchGoodsCategoryDTO.IdListDTO dto) {
        return ResponseData.data(categoryRpc.getCategoryVO(dto));
    }

    @ApiOperation("查询商品一级分类")
    @GetMapping("listLevel1Categories")
    public ResponseData<List<PCMerchGoodsCategoryVO.ListVO>> listLevel1Categories() {
        return ResponseData.data(categoryRpc.level1Categories());
    }

    @ApiOperation("查询所有的类目信息")
    @GetMapping("listAllCategories")
    public ResponseData<List<PCMerchGoodsCategoryVO.CategoryTreeVO>> getAllCategoryVO() {
        return ResponseData.data(categoryRpc.getAllCategoryVO());
    }
}
