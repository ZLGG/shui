package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayOfflineDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayOfflineQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayOfflineVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.trade.ITradePayOfflineRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author zst
 * @version 1.0
 * @date 2020/12/10 17:35
 */
@RestController
@RequestMapping("/platadmin/checkOfflinePay")
@Api(tags = "线下付款审核管理")
@Module(code = "auditOffline",parent = "finance",name = "线下审核" ,index = 4)
public class TradePayOfflineController {

    @DubboReference
    private ITradePayOfflineRpc iTradePayOfflineRpc;

    @ApiOperation("线下付款单据审核列表")
    @GetMapping("/list")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<TradePayOfflineVO.ListVO>> list(TradePayOfflineQTO.QTO qto) {
        return ResponseData.data(iTradePayOfflineRpc.pageData(qto));
    }

    @ApiOperation("转账确认显示详情")
    @GetMapping("/showDetail/{id}")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<TradeSettlementVO.ListVO>> showDetail(@PathVariable String id) {
        return ResponseData.data(iTradePayOfflineRpc.showDetail(id));
    }

    @ApiOperation("审核线下转账")
    @GetMapping("/verify")
    @Func(code = "edit" ,name = "修改")
    public ResponseData<String> verify(TradePayOfflineDTO.DTO dto) {
        iTradePayOfflineRpc.verify(dto);
        return ResponseData.data(dto);
    }


    @ApiOperation("审核通过/拒绝")
    @PutMapping(value = "/updateByRefuse/{id}")
    @Func(code = "edit" ,name = "修改")
    public ResponseData updateByRefuse(@PathVariable String id , @Valid @RequestBody TradePayOfflineDTO.RefuseDTO dto) {
        dto.setId(id);
        iTradePayOfflineRpc.updateByRefuse(dto);
        return ResponseData.success("success");
    }

    @ApiOperation("导出线下付款审核")
    @Log(module = "线下付款审核", func = "导出线下付款审核")
    @GetMapping(value = "/export")
    @Func(code = "export" ,name = "导出")
    public void export(TradePayOfflineQTO.IdListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = iTradePayOfflineRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }
}
