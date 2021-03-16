package com.gs.lshly.common.struct.bbb.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
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

        @ApiModelProperty("发票抬头[10个人 20企业]")
        private Integer invoiceRise;

        @ApiModelProperty("税号")
        private String taxNumber;

        @ApiModelProperty("公司名称")
        private String firmName;

        @ApiModelProperty("开户行")
        private String openingBank;

        @ApiModelProperty("发票类型[10增值税普通发票 20增值税专用发票]")
        private Integer invoiceType;

        @ApiModelProperty("是否默认发票[10默认 20普通]")
        private Integer isDefault;

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

        @ApiModelProperty("发票id")
        private String invoiceId;

        @ApiModelProperty("寄送地址id")
        private String invoiceAddressId;
    }

}
