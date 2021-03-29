package com.gs.lshly.facade.merchant.controller.pc.commodity;


import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.qto.PCMerchGoodsInfoQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.common.utils.HuToolExcelUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.commodity.IPCMerchAdminGoodsInfoRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author Starry
* @since 2020-10-08
*/
@RestController
@RequestMapping("/merchant/pc/goodsInfo")
@Api(tags = "商品信息管理-v1.1.0")
public class PCMerchGoodsInfoController {

    @DubboReference
    private IPCMerchAdminGoodsInfoRpc goodsInfoRpc;

    @ApiOperation("商品信息管理列表-v1.1.0")
    @GetMapping("")
    public ResponseData<PageData<PCMerchGoodsInfoVO.SpuListVO>> list(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto) {
        return ResponseData.data(goodsInfoRpc.pageData(qto));
    }

    @ApiOperation("库存报警商品信息管理列表")
    @GetMapping("/stockAlarm")
    public ResponseData<PageData<PCMerchGoodsInfoVO.StockAlarmGoodsVO>> listStockAlarm(PCMerchGoodsInfoQTO.GoodsInfoParamsQTO qto) {
        return ResponseData.data(goodsInfoRpc.pageStockAlarmGoods(qto));
    }

    @ApiOperation("商品信息管理详情-v1.1.0")
    @GetMapping(value = "/getGoodsDetail")
    public ResponseData<PCMerchGoodsInfoVO.DetailVO> get(PCMerchGoodsInfoDTO.EditIdsDTO dto) {
        return ResponseData.data(goodsInfoRpc.detailGoodsInfo(dto));
    }

    @ApiOperation("发布商品信息-v1.1.0")
    @PostMapping("")
    public ResponseData<Void> add(@Valid @RequestBody PCMerchGoodsInfoDTO.AddGoodsETO dto) {
        goodsInfoRpc.addGoodsInfo(dto);
        return ResponseData.success(MsgConst.ADD_SUCCESS);
    }

    @ApiOperation("删除商品信息管理")
    @DeleteMapping(value = "/{id}")
    public ResponseData<Void> delete(@PathVariable String id) {
        PCMerchGoodsInfoDTO.IdDTO dto = new PCMerchGoodsInfoDTO.IdDTO(id);
        goodsInfoRpc.deleteGoodsInfo(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("获取商品信息管理编辑详情-v1.1.0")
    @GetMapping(value = "/getEditDetail/{id}")
    public ResponseData<PCMerchGoodsInfoVO.EditDetailVO> getEditDetail(@PathVariable String id) {
        PCMerchGoodsInfoDTO.IdDTO dto = new PCMerchGoodsInfoDTO.IdDTO(id);
        return ResponseData.data(goodsInfoRpc.getEditDetailEto(dto));
    }

    @ApiOperation("修改商品信息管理-v1.1.0")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchGoodsInfoDTO.AddGoodsETO eto) {
        eto.setId(id);
        goodsInfoRpc.editGoodsInfo(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("批量上架商品")
    @PutMapping(value = "groundingBatch")
    public ResponseData<Void> groundingBatch(@RequestBody PCMerchGoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.groundingGoods(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("批量下架商品")
    @PutMapping(value = "underCarriageBatch")
    public ResponseData<Void> underCarriageBatch(@RequestBody PCMerchGoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.underCarriageGoods(dto);
        return ResponseData.success(MsgConst.UNDERCARRIAGE_SUCCESS);
    }

    @ApiOperation("批量删除商品")
    @PostMapping(value = "deleteBatches")
    public ResponseData<Void> deleteBatches(@RequestBody PCMerchGoodsInfoDTO.IdListDTO dto) {
        goodsInfoRpc.deleteGoodsBatches(dto);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }

    @ApiOperation("导出商品相关数据到Excel表格-v1.1.0")
    @GetMapping(value = "/exportData")
    public void export(@ApiIgnore HttpServletResponse response, PCMerchGoodsInfoQTO.IdListQTO qto) throws Exception {
        ExportDataDTO exportData = goodsInfoRpc.export(qto);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("下载模板-v1.1.0")
    @GetMapping(value = "/downExcel")
    public void downExcel(@ApiIgnore HttpServletResponse response) throws Exception{
        ExportDataDTO exportData = goodsInfoRpc.downExcelMode();
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("从Excel表格导入商品相关数据-已经废弃")
    @PostMapping(value = "/importData")
    public void importData( @RequestParam MultipartFile file,BaseDTO dto) throws Exception {
        List<PCMerchGoodsInfoDTO.ExcelGoodsDataETO> dataVOS = HuToolExcelUtil.importData(PCMerchGoodsInfoDTO.ExcelGoodsDataETO.class,file);
        goodsInfoRpc.addGoodsBatch(dataVOS,dto);
    }

    @ApiOperation("提供店铺楼层选择的商品列表-v1.1.0")
    @GetMapping("listShopFloorCommodityVO")
    public ResponseData<PageData<PCMerchGoodsInfoVO.ShopFloorCommodityVO>> listShopFloorCommodityVO(PCMerchGoodsInfoQTO.ShopFloorQTO qto) {
        return ResponseData.data(goodsInfoRpc.getShopFloorCommodityVO(qto));
    }

    @ApiOperation("提供店铺自定义类目关联选择的商品列表以及查看店铺自定义类目关联选择的商品列表")
    @GetMapping("listShopNavigationCommodityVO")
    public ResponseData<PageData<PCMerchGoodsInfoVO.ShopNavigationCommodityVO>> listShopNavigationCommodityVO(PCMerchGoodsInfoQTO.ShopNavigationQTO qto) {
        return ResponseData.data(goodsInfoRpc.pageShopNavigationCommodityVO(qto));
    }

    @ApiOperation("为活动提供商品列表选择")
    @GetMapping("pageGoodsActiveVO")
    public ResponseData<PageData<PCMerchGoodsInfoVO.GoodsActiveVO>> pageGoodsActiveVO(PCMerchGoodsInfoQTO.GoodsActiveQTO qto) {
        return ResponseData.data(goodsInfoRpc.pageGoodsActiveVO(qto));
    }

    @ApiOperation("为活动提供商品sku赠品列表选择")
    @PostMapping("pageSkuActiveGiftVO")
    public ResponseData<PageData<PCMerchGoodsInfoVO.SkuActivePageVO>> pageSkuActiveGiftVO(@RequestBody PCMerchGoodsInfoQTO.GoodsActiveQTO qto) {
        return ResponseData.data(goodsInfoRpc.pageSkuActivePageVO(qto));
    }

    @ApiOperation("查询活动商品对应的sku信息")
    @GetMapping("listSkuActiveVO")
    public ResponseData<List<PCMerchGoodsInfoVO.SkuActiveVO>> listSkuActiveVO(PCMerchGoodsInfoDTO.IdDTO dto) {
        return ResponseData.data(goodsInfoRpc.listSkuActiveVO(dto));
    }

    @ApiOperation("设置运费模板")
    @PostMapping("setGoodsTemplate")
    public ResponseData<Void> listShopNavigationCommodityVO(@RequestBody PCMerchGoodsInfoDTO.SettingGoodsTemplateDTO dto) {
        goodsInfoRpc.setGoodsTemplate(dto);
        return ResponseData.success(MsgConst.OPERATOR_SUCCESS);
    }

    @ApiOperation("为会员设置批发价提供商品sku列表")
    @GetMapping("getShopCategoryGoodsVO")
    public ResponseData<List<PCMerchGoodsInfoVO.ShopCategoryGoodsVO>> listShopNavigationCommodityVO(BaseDTO dto) {
        return ResponseData.data(goodsInfoRpc.getShopCategoryGoodsVO(dto));
    }



}
