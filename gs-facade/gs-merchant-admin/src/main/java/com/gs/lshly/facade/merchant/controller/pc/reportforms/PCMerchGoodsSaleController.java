package com.gs.lshly.facade.merchant.controller.pc.reportforms;

import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeGoodsDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeGoodsVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchGoodSaleRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeGoodsRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author zst
 * @version 1.0
 * @date 2021/1/4 15:40
 */
@RestController
@RequestMapping("/merchadmin/report")
@Api(tags = "商家报表")
public class PCMerchGoodsSaleController {

    @DubboReference
    private IPCMerchTradeGoodsRpc ipcMerchTradeGoodsRpc;

    @DubboReference
    private IPCMerchTradeRpc ipcMerchTradeRpc;

    @DubboReference
    private IPCMerchGoodSaleRpc ipcMerchGoodSaleRpc;

    @ApiOperation("商品销售分析列表")
    @PostMapping("/goodsSaleList")
    public ResponseData<List<TradeGoodsVO.GoodsSaleVO>> goodsSaleList(@RequestBody TradeGoodsDTO.GoodsSale dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.goodsSaleList(dto));
    }

    @ApiOperation("商品销售排行明细")
    @PostMapping("/goodsSaleListDetail")
    public ResponseData<List<TradeGoodsVO.GoodsSaleVO>> goodsSaleListDetail(@RequestBody TradeGoodsDTO.GoodsSale dto) {
        return ResponseData.data(ipcMerchTradeGoodsRpc.goodsSaleListDetail(dto));
    }

    @ApiOperation("交易数据分析")
    @PostMapping("/payDatelist")
    public ResponseData<TradeVO.PayDatelistVO> payDateList(@RequestBody TradeDTO.PayDateList dto) {
        return ResponseData.data(ipcMerchTradeRpc.payDateList(dto));
    }

    @ApiOperation("商家运营概括")
    @PostMapping("/operationList")
    public ResponseData<TradeVO.OperationlistVO> operationList(@RequestBody TradeDTO.OperationList dto) {
        return ResponseData.data(ipcMerchTradeRpc.operationList(dto));
    }


    @ApiOperation("导出商品销售分析列表")
    @Log(module = "商品销售分析列表", func = "导出商品销售分析列表")
    @GetMapping(value = "/exportGoodsSaleList")
    public void exportGoodsSaleList(TradeDTO.PayDateList qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = ipcMerchTradeGoodsRpc.exportGoodsSaleList(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("导出商品销售排行明细")
    @Log(module = "商品销售排行明细", func = "导出商品销售排行明细")
    @GetMapping(value = "/exportGoodsSaleListDetail")
    public void exportGoodsSaleListDetail(TradeDTO.PayDateList qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = ipcMerchTradeGoodsRpc.exportGoodsSaleListDetail(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("导出交易数据分析")
    @Log(module = "交易数据分析", func = "导出交易数据分析")
    @GetMapping(value = "/exportPayDateList")
    public void exportPayDateList(TradeQTO.PayDateList qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = ipcMerchGoodSaleRpc.exportPayDateList(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("导出商家运营概括")
    @Log(module = "商家运营概括", func = "导出商家运营概括")
    @GetMapping(value = "/exportOperationList")
    public void exportOperationList(TradeQTO.OperationList qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = ipcMerchGoodSaleRpc.exportOperationList(qo);
        ExcelUtil.export(exportData, response);
    }

}
