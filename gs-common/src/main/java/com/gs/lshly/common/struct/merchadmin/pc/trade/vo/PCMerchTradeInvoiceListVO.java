package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author zst
* @since 2020-12-22
*/
public abstract class PCMerchTradeInvoiceListVO implements Serializable {


    @Data
    @ApiModel("PCMerchTradeInvoiceListVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {


        @ApiModelProperty("id")
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


        @ApiModelProperty("物流公司代码")
        private String logisticsCode;


        @ApiModelProperty("快递单号")
        private String expressNumber;


        @ApiModelProperty("是否默认发票[10默认 20普通]")
        private Integer isDefault;


        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;
    }

}
