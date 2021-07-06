package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradePayDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
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
@Api(tags = "交易订单支付关联表管理")
@Module(code = "listAssociationPayment",parent = "transaction",name = "业务订单列表",index = 5)
public class TradePayRelationController {

    @DubboReference
    private ITradePayRpc TradePayRpc;


    @ApiOperation("支付单关联列表")
    @PostMapping("/relation/list")
    @Func(code = "view",name = "查")
    public ResponseData<PageData<TradePayVO.RelationDetailVO>> relationList(@Valid @RequestBody TradePayQTO.RelationQTO qto) {
        return ResponseData.data(TradePayRpc.relationList(qto));
    }

    @ApiOperation("导出支付单关联")
    @Log(module = "支付单关联", func = "导出支付单关联")
    @GetMapping(value = "/relationExport")
    @Func(code = "export",name = "导出")
    public void export(TradePayDTO.IdsDTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = TradePayRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("支付单关联详情")
    @GetMapping(value = "/relation/{id}")
    @Func(code = "view",name = "查")
    public ResponseData<TradePayVO.DetailVO> relationGet(@PathVariable String id) {
        return ResponseData.data(TradePayRpc.relationGet(new TradePayDTO.IdDTO(id)));
    }


}
