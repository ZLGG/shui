package com.gs.lshly.facade.merchant.controller.pc.trade;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.dto.PCMerchGoodsInfoDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeVO;
import com.gs.lshly.common.utils.HuToolExcelUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

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
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

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
    public ResponseData<PageData<PCMerchTradeListVO.tradeVO>> list(@RequestParam(name = "TradeListQto") PCMerchTradeQTO.TradeList qto) {
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
        exportData.setFileName("订单");
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("修改订单金额/修改运费")
    @PostMapping(value = "/editOrderAmount")
    public ResponseData<Void> editOrderAmount(@Valid @RequestBody PCMerchTradeDTO.orderAmountOrFreight dto) {
        pcMerchTradeRpc.editOrderAmount(dto);
        return ResponseData.data(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("从Excel表格导入发货信息")
    @PostMapping(value = "/importData")
    public ResponseData<PCMerchTradeVO.ExcelReturnVO> importData(@RequestParam("file") MultipartFile file)throws Exception {
        //List<PCMerchTradeListVO.importDate> dataVOS = HuToolExcelUtil.importData(PCMerchTradeListVO.importDate.class,file);
        byte[] excel = file.getBytes();
        return ResponseData.data(pcMerchTradeRpc.updateDeliveryInfoBatch(excel));
    }

    @ApiOperation("下载导入模版")
    @GetMapping(value = "/downExcelModel")
    public void downExcelModel(@ApiIgnore HttpServletResponse response)throws Exception {
        PCMerchTradeQTO.IdListQTO qo = new PCMerchTradeQTO.IdListQTO();
        qo.setType(1);
        ExportDataDTO exportData = pcMerchTradeRpc.downExcelModel(qo);
        exportData.setFileName("发货模版");
        ExcelUtil.export(exportData, response);
    }

}
