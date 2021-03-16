package com.gs.lshly.common.struct.bbb.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zst
* @since 2020-12-24
*/
public abstract class PCBbbTradeInvoiceAddressDTO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeInvoiceAddressDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("会员id")
        private String userId;

        @ApiModelProperty("会员名字")
        private String userName;

        @ApiModelProperty("省")
        private String province;

        @ApiModelProperty("省代码")
        private String provinceCode;

        @ApiModelProperty("市")
        private String city;

        @ApiModelProperty("市代码")
        private String cityCode;

        @ApiModelProperty("区")
        private String county;

        @ApiModelProperty("区代码")
        private String countyCode;

        @ApiModelProperty("街道")
        private String street;

        @ApiModelProperty("邮政编码")
        private String postalCode;

        @ApiModelProperty("收货人姓名")
        private String consigneeName;

        @ApiModelProperty("收货人电话")
        private String consigneePhone;

        @ApiModelProperty("是否默认地址[10默认地址 20普通地址]")
        private Integer isDefault;
    }

    @Data
    @ApiModel("PCBbbTradeInvoiceAddressDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCBbbTradeInvoiceAddressDTO.IsDefaultDTO")
    @AllArgsConstructor
    public static class IsDefaultDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("是否默认地址[10默认地址 20普通地址]")
        private Integer isDefault;
    }

}
