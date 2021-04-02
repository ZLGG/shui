package com.gs.lshly.facade.merchant.controller.pc.trade;

import com.gs.lshly.common.constants.MsgConst;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.ExportDataDTO;
import com.gs.lshly.common.struct.common.CommonLogisticsCompanyVO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.dto.PCMerchTradeDeliveryDTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeDeliveryQTO;
import com.gs.lshly.common.struct.merchadmin.pc.trade.vo.PCMerchTradeDeliveryVO;
import com.gs.lshly.common.utils.ExcelUtil;
import com.gs.lshly.middleware.log.Log;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeDeliveryRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
* <p>
*  前端控制器
* </p>
*
* @author oy
* @since 2020-11-16
*/
@RestController
@RequestMapping("/merchadmin/pc/tradeDelivery")
@Api(tags = "交易订单发货表管理")
public class PCMerchTradeDeliveryController {

    @DubboReference
    private IPCMerchTradeDeliveryRpc pcMerchTradeDeliveryRpc;

    @ApiOperation("交易订单发货表列表")
    @GetMapping("/list")
    public ResponseData<PageData<PCMerchTradeDeliveryVO.ListVO>> list(PCMerchTradeDeliveryQTO.QTO qto) {
        return ResponseData.data(pcMerchTradeDeliveryRpc.pageData(qto));
    }

    @ApiOperation("导出发货单")
    @Log(module = "发货单", func = "导出发货单")
    @GetMapping(value = "/export")
    public void export(PCMerchTradeDeliveryQTO.IdListQTO qo, @ApiIgnore HttpServletResponse response) throws Exception {
        ExportDataDTO exportData = pcMerchTradeDeliveryRpc.export(qo);
        ExcelUtil.export(exportData, response);
    }

    @ApiOperation("交易订单发货表详情")
    @GetMapping(value = "/{id}")
    public ResponseData<PCMerchTradeDeliveryVO.DetailVO> get(@PathVariable String id) {
        return ResponseData.data(pcMerchTradeDeliveryRpc.detailTradeDelivery(new PCMerchTradeDeliveryDTO.IdDTO(id)));
    }

    @ApiOperation("发货")
    @PostMapping("/doDelivery")
    public ResponseData<Void> doDelivery(@Valid @RequestBody PCMerchTradeDeliveryDTO.deliveryDTO dto) {
            pcMerchTradeDeliveryRpc.addTradeDelivery(dto);
        return ResponseData.success(MsgConst.SUCCESS);
    }


    @ApiOperation("修改交易订单发货表")
    @PutMapping(value = "/{id}")
    public ResponseData<Void> update(@PathVariable String id, @Valid @RequestBody PCMerchTradeDeliveryDTO.ETO eto) {
        eto.setId(id);
        pcMerchTradeDeliveryRpc.editTradeDelivery(eto);
        return ResponseData.success(MsgConst.UPDATE_SUCCESS);
    }

    @ApiOperation("商家物流列表")
    @GetMapping(value = "/shopLogisticsList")
    public ResponseData<List<CommonLogisticsCompanyVO.DetailVO>> listShopLogisticsCompany(PCMerchTradeDeliveryDTO.IdDTO dto) {
        return ResponseData.data(pcMerchTradeDeliveryRpc.listShopLogisticsCompany(dto));
    }

    @ApiOperation("自提核销")
    @PostMapping("/takeGoodsCodeCheck")
    public ResponseData<Void> takeGoodsCodeCheck(@Valid @RequestBody PCMerchTradeDeliveryDTO.takeGoodsCodeCheckDTO dto) {
        pcMerchTradeDeliveryRpc.addTakeGoodsCodeCheck(dto);
        return ResponseData.success(MsgConst.SUCCESS);
    }

}
