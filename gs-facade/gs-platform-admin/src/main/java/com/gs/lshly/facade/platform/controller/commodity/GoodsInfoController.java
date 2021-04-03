package com.gs.lshly.facade.platform.controller.commodity;

import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Module(code = "commodityManagement", parent = "commodity", name = "商品管理", index =1)
public class GoodsInfoController {
    @DubboReference
    private IGoodsInfoRpc goodsInfoRpc;

    @ApiOperation("商品管理列表")
    @GetMapping("")
    @Func(code="view", name = "查看")
    public ResponseData<PageData<GoodsInfoVO.SpuListVO>> page(GoodsInfoQTO.QTO qto) {
        return ResponseData.data(goodsInfoRpc.pageGoodsData(qto));
    }

    @ApiOperation("商品管理列表详情")
    @GetMapping(value = "{id}")
    @Func(code="view", name = "查看")
    public ResponseData<GoodsInfoVO.DetailVO> page(@PathVariable String id) {
        return ResponseData.data(goodsInfoRpc.getGoodsDetail(new GoodsInfoDTO.IdDTO(id)));
    }

    @ApiOperation("批量下架商品")
    @PutMapping(value = "underCarriageBatch")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> underCarriageBatch(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.underCarriageGoods(dto);
        return ResponseData.success(MsgConst.UNDERCARRIAGE_SUCCESS);
    }

    @ApiOperation("批量删除商品")
    @DeleteMapping(value = "deleteBatches")
    @Func(code="delete", name = "删除")
    public ResponseData<Void> deleteBatches(GoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.deleteGoodsBatches(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("审核商品")
    @PutMapping(value = "checkGoods")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> checkGoods(@Valid @RequestBody GoodsInfoDTO.CheckGoodsDTO dto) {
        goodsInfoRpc.checkGoods(dto);
        return ResponseData.success(MsgConst.DETERMINE_SUCCESS);
    }

    @ApiOperation("批量审核商品")
    @PostMapping(value = "checkGoodsBatches")
    @Func(code="edit", name = "修改")
    public ResponseData<Void> checkGoodsBatches(@Valid @RequestBody GoodsInfoDTO.CheckGoodsBatchesDTO dto) {
        goodsInfoRpc.checkGoodsBatches(dto);
        return ResponseData.success(MsgConst.DETERMINE_SUCCESS);
    }

    @ApiOperation("提供楼层要选的商品列表")
    @GetMapping(value = "getShopFloorCommodityVO")
    @Func(code="view", name = "查看")
    public ResponseData<PageData<GoodsInfoVO.ShopFloorCommodityVO>> getShopFloorCommodityVO(GoodsInfoQTO.ShopFloorQTO qto) {
        return ResponseData.data(goodsInfoRpc.getShopFloorCommodityVO(qto));
    }

    @ApiOperation("提供扶贫楼层要选的商品列表")
    @GetMapping(value = "getFupinFloorCommodityVO")
    @Func(code="view", name = "查看")
    public ResponseData<PageData<GoodsInfoVO.FupinFloorCommodityVO>> getFupinFloorCommodityVO(GoodsInfoQTO.FupinFloorQTO qto) {
        return ResponseData.data(goodsInfoRpc.getFupinFloorCommodityVO(qto));
    }

    @ApiOperation("查看三级类目下关联的商品列表")
    @GetMapping(value = "getBindCategoryCommodityVO")
    @Func(code="view", name = "查看")
    public ResponseData<PageData<GoodsInfoVO.BindCategoryGoodsVO>> getBindCategoryGoodsVO(GoodsInfoQTO.CategoryIdQTO qto) {
        return ResponseData.data(goodsInfoRpc.getBindCategoryGoodsVO(qto));
    }
}
