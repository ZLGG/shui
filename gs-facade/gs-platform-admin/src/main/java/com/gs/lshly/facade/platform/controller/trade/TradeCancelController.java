package com.gs.lshly.facade.platform.controller.trade;
import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeCancelDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeCancelQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeCancelVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeCancelRpc;
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
* @author oy
* @since 2020-11-21
*/
@RestController
@RequestMapping("/platadmin/tradeCancel")
@Api(tags = "交易订单取消表管理")
public class TradeCancelController {

    @DubboReference
    private ITradeCancelRpc TradeCancelRpc;

    @ApiOperation("交易订单取消表列表")
    @GetMapping("/list")
    public ResponseData<PageData<TradeCancelVO.ListVO>> list(TradeCancelQTO.QTO qto) {
        return ResponseData.data(TradeCancelRpc.pageData(qto));
    }

    @ApiOperation("导出业务订单")
    @Log(module = "业务订单", func = "导出业务订单")
    @GetMapping(value = "/export")
    public void export(TradeCancelQTO.IdListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = TradeCancelRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("交易订单取消表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<TradeCancelVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(TradeCancelRpc.detailTradeCancel(new TradeCancelDTO.IdDTO(id)));
    }

    @ApiOperation("交易订单取消表删除")
    @PostMapping(value = "/delete")
    public ResponseData<Void> delete(@RequestBody TradeCancelQTO.IdListQTO ids) {
        TradeCancelRpc.delete(ids);
        return ResponseData.success(MsgConst.DELETE_SUCCESS);
    }


}
