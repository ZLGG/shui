package com.gs.lshly.common.struct.bbc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2021-01-15
*/
public abstract class BbcTradeInvoiceVO implements Serializable {

    @Data
    @ApiModel("BbcTradeInvoiceVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("店铺id")
        private String shopId;


        @ApiModelProperty("会员id")
        private String userId;


        @ApiModelProperty("订单编号")
        private String tradeId;


        @ApiModelProperty("发票抬头")
        private Integer invoiceRise;


        @ApiModelProperty("会员名字")
        private String userName;


        @ApiModelProperty("发票号")
        private String invoiceNumber;


        @ApiModelProperty("纳税人识别号")
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


        @ApiModelProperty("是否为默认发票")
        private Integer isDefault;


        @ApiModelProperty("快递单号")
        private String expressNumber;




    }

    @Data
    @ApiModel("BbcTradeInvoiceVO.DetailVO")
    public static class DetailVO extends ListVO {

    }


    @Data
    @ApiModel("BbcTradeInvoiceVO.BbcListVO")
    @Accessors(chain = true)
    public static class BbcListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("发票抬头")
        private Integer invoiceRise;

        @ApiModelProperty("发票号")
        private String invoiceNumber;

        @ApiModelProperty("纳税人识别号")
        private String taxNumber;

        @ApiModelProperty("公司名称")
        private String firmName;

        @ApiModelProperty("发票类型[10增值税普通发票 20增值税专用发票]")
        private Integer invoiceType;

        @ApiModelProperty("开票状态[10待开 20已开 30已寄出 40已完成]")
        private Integer invoiceStatus;

        @ApiModelProperty("是否为默认发票")
        private Integer isDefault;


    }
}
