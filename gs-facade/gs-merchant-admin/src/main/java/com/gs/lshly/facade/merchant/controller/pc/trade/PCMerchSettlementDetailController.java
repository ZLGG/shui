package com.gs.lshly.facade.merchant.controller.pc.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeSettlementDetailDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeSettlementDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementDetailVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMarketSettlementDetailRpc;
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
@RequestMapping("/merchadmin/pc/tradeSettlementDetail")
@Api(tags = "商家结算明细表管理")
public class PCMerchSettlementDetailController {

    @DubboReference
    private IPCMarketSettlementDetailRpc ipcMarketSettlementDetailRpc;


    @ApiOperation("结算明细表列表")
    @PostMapping("list")
    public ResponseData<PageData<TradeSettlementDetailVO.ListVO>> list(@RequestBody TradeSettlementDetailQTO.ListQTO qto) {
        return ResponseData.data(ipcMarketSettlementDetailRpc.pageData(qto));
    }

    @ApiOperation("结算明细表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<TradeSettlementDetailVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(ipcMarketSettlementDetailRpc.detailTradeSettlementDetail(new TradeSettlementDetailDTO.IdDTO(id)));
    }

    @ApiOperation("导出结算明细表")
    @Log(module = "导出结算明细表", func = "导出结算明细表")
    @GetMapping(value = "/export")
    public void export(TradeSettlementDetailQTO.ListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = ipcMarketSettlementDetailRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

}
