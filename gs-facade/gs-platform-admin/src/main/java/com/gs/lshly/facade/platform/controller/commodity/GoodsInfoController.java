package com.gs.lshly.facade.platform.controller.commodity;

import java.util.List;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;
import com.gs.lshly.common.struct.platadmin.commodity.qto.GoodsInfoQTO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsInfoVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.platadmin.commodity.IGoodsInfoRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author Starry
 * @Date 15:14 2020/10/14
 */
@RestController
@RequestMapping("/platform/GoodsInfo")
@Api(tags = "商品管理-v1.1.0")
@Module(code = "commodityManagement", parent = "commodity", name = "商品管理", index = 1)
public class GoodsInfoController {
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;

    @ApiOperation("商品管理列表")
    @GetMapping("")
    @Func(code = "view", name = "查看列表")
    public ResponseData<PageData<GoodsInfoVO.SpuListVO>> page(GoodsInfoQTO.QTO qto) {
        return ResponseData.data(goodsInfoRpc.pageGoodsData(qto));
    }

    @ApiOperation("跟据分类ID查询商品列表")
    @GetMapping("/listByCategoryId")
    @Func(code = "listByCategoryId", name = "跟据分类查询列表")
    public ResponseData<List<GoodsInfoVO.SpuListVO>> listByCategoryId(GoodsInfoQTO.ListQTO qto) {
        return ResponseData.data(goodsInfoRpc.listGoodsData(qto));
    }
    
    @ApiOperation("商品管理列表详情")
    @GetMapping(value = "{id}")
    @Func(code = "detail", name = "查看详情")
    public ResponseData<GoodsInfoVO.DetailVO> page(@PathVariable String id, @RequestParam("type")Integer type) {
        return ResponseData.data(goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(id),type));
    }

    @ApiOperation("批量上架商品")
    @PutMapping(value = "upCarriageBatch")
    @Func(code = "upCarriageBatch", name = "批量上架")
    public ResponseData<Void> upCarriageBatch(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.upCarriageGoods(dto);
        return ResponseData.success(MsgConst.GROUNDING_SUCCESS);
    }

    @ApiOperation("批量下架商品")
    @PutMapping(value = "underCarriageBatch")
    @Func(code = "underCarriageBatch", name = "批量下架")
    public ResponseData<Void> underCarriageBatch(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.underCarriageGoods(dto);
        return ResponseData.success(MsgConst.UNDERCARRIAGE_SUCCESS);
    }

    @ApiOperation("批量删除商品")
    @DeleteMapping(value = "deleteBatches")
    @Func(code = "deleteBatches", name = "删除")
    public ResponseData<Void> deleteBatches(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.deleteGoodsBatches(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("审核商品")
    @PutMapping(value = "checkGoods")
    @Func(code = "checkGoods", name = "审核")
    public ResponseData<Void> checkGoods(@Valid @RequestBody GoodsInfoDTO.CheckGoodsDTO dto) {
        goodsInfoRpc.checkGoods(dto);
        return ResponseData.success(MsgConst.DETERMINE_SUCCESS);
    }

    @ApiOperation("批量审核商品")
    @PostMapping(value = "checkGoodsBatches")
    @Func(code = "checkGoodsBatches", name = "批量审核")
    public ResponseData<Void> checkGoodsBatches(@Valid @RequestBody GoodsInfoDTO.CheckGoodsBatchesDTO dto) {
        goodsInfoRpc.checkGoodsBatches(dto);
        return ResponseData.success(MsgConst.DETERMINE_SUCCESS);
    }

    @ApiOperation("提供楼层要选的商品列表")
    @GetMapping(value = "getShopFloorCommodityVO")
    public ResponseData<PageData<GoodsInfoVO.ShopFloorCommodityVO>> getShopFloorCommodityVO(GoodsInfoQTO.ShopFloorQTO qto) {
        return ResponseData.data(goodsInfoRpc.getShopFloorCommodityVO(qto));
    }

    @ApiOperation("提供扶贫楼层要选的商品列表")
    @GetMapping(value = "getFupinFloorCommodityVO")
    public ResponseData<PageData<GoodsInfoVO.FupinFloorCommodityVO>> getFupinFloorCommodityVO(GoodsInfoQTO.FupinFloorQTO qto) {
        return ResponseData.data(goodsInfoRpc.getFupinFloorCommodityVO(qto));
    }

    @ApiOperation("查看三级类目下关联的商品列表")
    @GetMapping(value = "getBindCategoryCommodityVO")
    public ResponseData<PageData<GoodsInfoVO.BindCategoryGoodsVO>> getBindCategoryGoodsVO(GoodsInfoQTO.CategoryIdQTO qto) {
        return ResponseData.data(goodsInfoRpc.getBindCategoryGoodsVO(qto));
    }
}
