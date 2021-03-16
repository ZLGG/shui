package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class PCMerchTradeInvoiceDTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeDTO.DTO")
    @Accessors(chain = true)
    public static class DTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("订单编号")
        private String tradeId;

        @ApiModelProperty("发票抬头")
        private String invoiceRise;

        @ApiModelProperty("发票号")
        private String invoiceNumber;

        @ApiModelProperty("税号")
        private String taxNumber;

        @ApiModelProperty("公司名称")
        private String firmName;

        @ApiModelProperty("注册地址")
        private String registerAddress;

        @ApiModelProperty("账户")
        private String accountNumber;

        @ApiModelProperty("电话")
        private String phone;

        @ApiModelProperty("开户行")
        private String openingBank;

        @ApiModelProperty("发票类型[10增值税普通发票 20增值税专用发票]")
        private Integer invoiceType;

        @ApiModelProperty("开票状态[10待开 20已开 30已寄出 40已完成]")
        private Integer invoiceStatus;

    }

}
