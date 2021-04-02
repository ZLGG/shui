package com.gs.lshly.facade.merchant.controller.pc.trade;

import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.struct.merchadmin.pc.trade.qto.PCMerchTradeInvoiceQTO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradePayOfflineVO;
import com.gs.lshly.common.struct.platadmin.trade.vo.TradeSettlementVO;
import com.gs.lshly.middleware.auth.rbac.Func;
import com.gs.lshly.middleware.auth.rbac.Module;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeInvoiceRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zst
 * @version 1.0
 * @date 2020/12/14 13:40
 */
@RestController
@RequestMapping("/merchadmin/invoice")
@Api(tags = "发票管理")
public class PCMerchTradeInvoiceController {

    @DubboReference
    private IPCMerchTradeInvoiceRpc iPCMerchTradeInvoiceRpc;

    @ApiOperation("发票列表")
    @GetMapping("/list")
    public ResponseData<PageData<TradePayOfflineVO.ListVO>> list(PCMerchTradeInvoiceQTO.QTO qto) {
        return ResponseData.data(iPCMerchTradeInvoiceRpc.invoiceListPageData(qto));
    }

    @ApiOperation("开票")
    @GetMapping("/issueInvoice")
    public ResponseData<PageData<TradeSettlementVO.ListVO>> issueInvoice(PCMerchTradeInvoiceQTO.IssueQTO qto) {
        return ResponseData.data(iPCMerchTradeInvoiceRpc.issueInvoice(qto));
    }

    @ApiOperation("寄出发票")
    @GetMapping("/mailInvoice")
    public ResponseData<String> mailInvoice(PCMerchTradeInvoiceQTO.MailQTO qto) {
        return ResponseData.data(iPCMerchTradeInvoiceRpc.mailInvoice(qto));
    }

}
