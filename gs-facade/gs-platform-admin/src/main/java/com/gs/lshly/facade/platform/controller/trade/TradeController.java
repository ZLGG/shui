package com.gs.lshly.facade.platform.controller.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.platadmin.trade.dto.TradeDTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradePayQTO;
import com.gs.lshly.common.struct.platadmin.trade.qto.TradeQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeListVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.platadmin.trade.ITradeRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-11-16
*/
@RestController
@RequestMapping("/platadmin/trade")
@Api(tags = "交易订单表管理")
public class TradeController {

    @DubboReference
    private ITradeRpc TradeRpc;

    @ApiOperation("交易订单表列表")
    @GetMapping("/list")
    public ResponseData<PageData<TradeListVO.tradeVO>> list(TradeQTO.TradeList qto) {
        return ResponseData.data(TradeRpc.tradeListPageData(qto));
    }
    @ApiOperation("导出业务订单")
    @Log(module = "业务订单", func = "导出业务订单")
    @GetMapping(value = "/export")
    public void export(TradeQTO.IdListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = TradeRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("交易订单表详情")
    @GetMapping(value = "/detail")
    public ResponseData<TradeListVO.tradeVO> detail(TradeDTO.IdDTO dto) {
        return ResponseData.data(TradeRpc.detail(dto));
    }




}
