package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeMarginDetailVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeMarginDetailRpc;
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
* @since 2020-12-10
*/
@RestController
@RequestMapping("/platadmin/tradeMarginDetail")
@Api(tags = "保证金明细")
public class TradeMarginDetailController {

    @DubboReference
    private ITradeMarginDetailRpc TradeMarginDetailRpc;

    @ApiOperation("保证金明细列表")
    @GetMapping("/list")
    @Func(code = "view" ,name = "查看")
    public ResponseData<PageData<TradeMarginDetailVO.ListVO>> list(TradeMarginDetailQTO.QTO qto) {
        return ResponseData.data(TradeMarginDetailRpc.pageData(qto));
    }

    @ApiOperation("导出保证金明细")
    @Log(module = "保证金明细", func = "导出保证金明细")
    @GetMapping(value = "/export")
    @Func(code = "export" ,name = "导出")
    public void export(TradeMarginDetailQTO.IdListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = TradeMarginDetailRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }
}
