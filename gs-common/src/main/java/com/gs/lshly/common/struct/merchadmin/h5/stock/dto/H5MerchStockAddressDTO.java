package com.gs.lshly.common.struct.merchadmin.h5.stock.dto;
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
* @author zst
* @since 2021-01-21
*/
public abstract class H5MerchStockAddressDTO implements Serializable {

    @Data
    @ApiModel("H5MerchStockAddressDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("归属者ID")
        private String ownerId;

        @ApiModelProperty("归属者类型[10=店铺 20=会员]")
        private Integer ownerType;

        @ApiModelProperty("标签名称")
        private String lableName;

        @ApiModelProperty("省")
        private String province;

        @ApiModelProperty("省代码")
        private String provinceCode;

        @ApiModelProperty("市")
        private String city;

        @ApiModelProperty("市代码")
        private String cityCode;

        @ApiModelProperty("县/区")
        private String county;

        @ApiModelProperty("区代号")
        private String countyCode;

        @ApiModelProperty("街道地址")
        private String street;

        @ApiModelProperty("邮政编码")
        private String postalCode;

        @ApiModelProperty("详细地址")
        private String reals;

        @ApiModelProperty("")
        private BigDecimal longitude;

        @ApiModelProperty("")
        private BigDecimal latitude;

        @ApiModelProperty("联系人")
        private String contactsName;

        @ApiModelProperty("手机号")
        private String contactsPhone;
    }

    @Data
    @ApiModel("H5MerchStockAddressDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;
    }


}
