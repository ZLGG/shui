package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class PCMerchTradeInvoiceQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeInvoiceQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("发票号码")
        private String invoiceNumber;

        @ApiModelProperty("订单号")
        private String tradeId;

        @ApiModelProperty("状态10待开20已开30已寄出40已完成")
        private Integer invoiceStatus;

    }

    @Data
    @ApiModel("PCMerchTradeInvoiceQTO.IssueQTO")
    @Accessors(chain = true)
    public static class IssueQTO extends BaseQTO {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("发票号码")
        private String invoiceNumber;
    }

    @Data
    @ApiModel("PCMerchTradeInvoiceQTO.MailQTO")
    @Accessors(chain = true)
    public static class MailQTO extends BaseQTO {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("物流公司代码")
        private String logisticsCode;

        @ApiModelProperty("快递单号")
        private String expressNumber;
    }

}
