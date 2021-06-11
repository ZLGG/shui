package com.gs.lshly.facade.merchant.controller.pc.trade;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeListVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeRpc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
* <p>
*  前端控制器
* </p>
*
* @author zdf
* @since 2020-11-16
*/
@RestController
@RequestMapping("/merchadmin/pc/trade")
@Api(tags = "交易订单表管理")
public class PCMerchTradeController {

    @DubboReference
    private IPCMerchTradeRpc pcMerchTradeRpc;

    @ApiOperation("交易订单表列表")
    @GetMapping("/list")
    public ResponseData<PageData<PCMerchTradeListVO.tradeVO>> list(PCMerchTradeQTO.TradeList qto) {
        return ResponseData.data(pcMerchTradeRpc.tradeListPageData(qto));
    }

    @ApiOperation("交易订单表详情")
    @GetMapping(value = "/detail")
    public ResponseData<PCMerchTradeListVO.tradeVO> detail(PCMerchTradeDTO.IdDTO dto) {
        return ResponseData.data(pcMerchTradeRpc.detail(dto));
    }

    @ApiOperation("导出交易订单")
    @Log(module = "交易订单", func = "导出交易订单")
    @GetMapping(value = "/export")
    public void export(PCMerchTradeQTO.IdListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = pcMerchTradeRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("修改订单金额/修改运费")
    @PostMapping(value = "/editOrderAmount")
    public ResponseData<Void> editOrderAmount(@Valid @RequestBody PCMerchTradeDTO.orderAmountOrFreight dto) {
        pcMerchTradeRpc.editOrderAmount(dto);
        return ResponseData.data(MsgConst.UPDATE_SUCCESS);
    }





}
