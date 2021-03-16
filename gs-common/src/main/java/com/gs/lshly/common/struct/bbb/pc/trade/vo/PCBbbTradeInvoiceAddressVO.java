package com.gs.lshly.common.struct.bbb.pc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author zst
* @since 2020-12-24
*/
public abstract class PCBbbTradeInvoiceAddressVO implements Serializable {

    @Data
    @ApiModel("PCBbbTradeInvoiceAddressVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
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

        @ApiModelProperty("街道地址")
        private String street;

        @ApiModelProperty("邮政编码")
        private String postalCode;

        @ApiModelProperty("收货人姓名")
        private String consigneeName;

        @ApiModelProperty("收货人电话")
        private String consigneePhone;

        @ApiModelProperty("是否默认地址[10默认 20普通]")
        private Integer isDefault;

    }

    @Data
    @ApiModel("PCBbbTradeInvoiceAddressVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
