package com.gs.lshly.facade.merchant.controller.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.common.ICommonRegionRpc;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMarketSettlementRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
* <p>
*  前端控制器
* </p>
*
* @author zst
* @since 2020-12-01
*/
@RestController
@RequestMapping("/merchadmin/pc/settlement")
@Api(tags = "结算管理")
public class PCMerchSettlementController {

    @DubboReference
    private ICommonRegionRpc commonRegionRpc;

    @DubboReference
    private IPCMarketSettlementRpc ipcMarketSettlementRpc;

    @ApiOperation("汇总商家订单数据")
    @GetMapping("/orderDataSettlement")
    public ResponseData orderDataSettlement() {

        //此处将订单表数据根据商家汇总到结算表
        return ResponseData.success("success");

    }

    @ApiOperation("商家结算列表")
    @PostMapping("/list")
    public ResponseData<PageData<TradeSettlementVO.ListVO>> list(@RequestBody TradeSettlementQTO.PcQTO qto) {
        return ResponseData.data(ipcMarketSettlementRpc.settlementPageData(qto));
    }

    @ApiOperation("商家结算详情")
    @GetMapping(value = "/{id}")
    public ResponseData<TradeSettlementVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(ipcMarketSettlementRpc.detailTradeSettlement(new TradeSettlementDTO.IdOfDTO(id)));
    }


    @ApiOperation("导出结算汇总表")
    @Log(module = "结算汇总表管理", func = "导出结算汇总表")
    @GetMapping(value = "/export")
    public void export(TradeSettlementQTO.PcQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = ipcMarketSettlementRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }


    @ApiOperation("账单下载")
    @Log(module = "账单下载", func = "账单下载")
    @GetMapping(value = "/DownloadExport")
    public void DownloadExport(TradeSettlementQTO.DownloadExportQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = ipcMarketSettlementRpc.DownloadExport(qo);
        ExcelUtil.export(exportData, response);
    }



}
