package com.gs.lshly.common.struct.bbb.h5.trade.vo;
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
public abstract class H5BbbTradeInvoiceAddressVO implements Serializable {

    @Data
    @ApiModel("H5BbbTradeInvoiceAddressVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("发票id")
        private String invoiceId;


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


        @ApiModelProperty("是否默认地址")
        private Integer isDefault;




    }

    @Data
    @ApiModel("H5BbbTradeInvoiceAddressVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
