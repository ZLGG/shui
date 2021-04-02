package com.gs.lshly.common.struct.bbb.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-24
*/
public abstract class PCBbbTradeInvoiceDTO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeInvoiceDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("是否录入")
        private Integer isAdd;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("会员id")
        private String userId;

        @ApiModelProperty("订单编号")
        private String tradeId;

        @ApiModelProperty("发票类型")
        private Integer invoiceRise;

        @ApiModelProperty("发票抬头")
        private String invoiceName;

        @ApiModelProperty("会员名字")
        private String userName;

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

        @ApiModelProperty("是否为默认发票")
        private Integer isDefault;

        @ApiModelProperty("快递单号")
        private String expressNumber;

        @ApiModelProperty("发票内容")
        private String invoiceContent;

        @ApiModelProperty("联系人")
        private String contactsUser;

        @ApiModelProperty("联系人邮箱")
        private String contactsEmail;

    }

    @Data
    @ApiModel("PCBbbTradeInvoiceDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCBbbTradeInvoiceDTO.IsDefaultDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IsDefaultDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("是否默认发票[10默认 20普通]")
        private Integer isDefault;
    }

    @Data
    @ApiModel("PCBbbTradeInvoiceDTO.UpdateByIsDefaultIsDefaultDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateByIsDefaultIsDefaultDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("发票模版id")
        private String invoiceId;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("寄送地址id")
        private String invoiceAddressId;

        @ApiModelProperty("邮箱")
        private String emailNum;

        @ApiModelProperty("发票内容")
        private String invoiceContent;
    }
    @Data
    @ApiModel("PCBbbTradeInvoiceDTO.clickBilingDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class clickBilingDTO extends BaseDTO {
        @ApiModelProperty("订单ID")
        private String tradeId;

    }

}
