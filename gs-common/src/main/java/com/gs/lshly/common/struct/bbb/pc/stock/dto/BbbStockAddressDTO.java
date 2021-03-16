package com.gs.lshly.common.struct.bbb.pc.stock.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author zzg
* @since 2020-11-02
*/
public abstract class BbbStockAddressDTO implements Serializable {

    @Data
    @ApiModel("BbbStockAddressDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

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

        @ApiModelProperty("区代码")
        private String countyCode;

        @ApiModelProperty("经度")
        private BigDecimal longitude;

        @ApiModelProperty("纬度")
        private BigDecimal latitude;

        @ApiModelProperty("街道地址")
        private String street;

        @ApiModelProperty("邮政编码")
        private String postalCode;

        @ApiModelProperty("联系人")
        private String contactsName;

        @ApiModelProperty("手机号")
        private String contactsPhone;

    }
    @Data
    @ApiModel("BbbStockAddressDTO.DetailETO")
    public static class DetailETO extends ETO {

        @ApiModelProperty("是否设置成默认地址(1 =是 ，0 =否")
        private Integer isDefault;

        @ApiModelProperty("地址类型[10=收货 20=发票 30=发货 40=退货]")
        private Integer addressType;

    }


    @Data
    @ApiModel("BbbStockAddressDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;

    }

    @Data
    @ApiModel("BbbStockAddressDTO.SetDefaultDTO")
    public static class SetDefaultDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String addressId;

        @ApiModelProperty(value = "地址类型[10=收货 20=发票 30=发货 40=退货]")
        private Integer addressType;

    }

    @Data
    @ApiModel("BbbStockAddressDTO.EditDTO")
    public static class EditDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String addressId;

        @ApiModelProperty(value = "地址类型[10=收货 20=发票 30=发货 40=退货]")
        private Integer addressType;

    }

    @Data
    @ApiModel("BbbStockAddressDTO.AddressTypeDTO")
    public static class AddressTypeDTO extends BaseDTO {

        @ApiModelProperty(value = "地址类型[10=收货 20=发票 30=发货 40=退货]")
        private Integer addressType;

    }


}
