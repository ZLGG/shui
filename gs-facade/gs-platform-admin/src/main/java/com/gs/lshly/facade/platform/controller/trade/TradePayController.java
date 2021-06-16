package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeMarginDetailQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.trade.ITradePayRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-11-22
*/
@RestController
@RequestMapping("/platadmin/tradePay")
@Api(tags = "交易订单支付表管理")
@Module(code = "listPayment",parent = "transaction",name = "支付单列表",index = 6)
public class  TradePayController {

    @DubboReference
    private ITradePayRpc TradePayRpc;

    @ApiOperation("支付单列表")
    @PostMapping("/list")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<TradePayVO.ListVO>> list(@Valid @RequestBody TradePayQTO.QTO qto) {
        return ResponseData.data(TradePayRpc.pageData(qto));
    }

    @ApiOperation("交易订单支付表详情")
    @GetMapping(value = "/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<TradePayVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(TradePayRpc.detailTradePay(new TradePayDTO.IdDTO(id)));
    }

    @ApiOperation("导出支付单")
    @Log(module = "支付单", func = "导出支付单")
    @GetMapping(value = "/export")
    public void payExport(TradePayQTO.IdListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = TradePayRpc.payExport(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("删除")
    @PostMapping(value = "delete")
    @Func(code = "view",name = "删除")
    public ResponseData<Void> delete(@RequestBody TradePayQTO.IdListQTO ids) {
        TradePayRpc.delete(ids);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }



}
