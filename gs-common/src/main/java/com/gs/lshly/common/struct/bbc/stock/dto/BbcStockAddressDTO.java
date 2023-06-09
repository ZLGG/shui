package com.gs.lshly.common.struct.bbc.stock.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author zzg
* @since 2020-11-02
*/
public abstract class BbcStockAddressDTO implements Serializable {

    @Data
    @ApiModel("BbcStockAddressDTO.ETO")
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

        @ApiModelProperty("详细地址")
        private String reals;

        @ApiModelProperty("完整地址")
        private String fullAddress;

        @ApiModelProperty("联系人")
        private String contactsName;

        @ApiModelProperty("手机号")
        private String contactsPhone;

        @ApiModelProperty("默认收货地址[0=否 1=是]")
        private Integer isDefault;

    }

    @Data
    @ApiModel("BbcStockAddressDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;

    }

    @Data
    @ApiModel("BbcStockAddressDTO.AnalyzeParamsDTO")
    public static class AnalyzeParamsDTO implements Serializable{

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("级别")
        private String level;

    }

    @Data
    @ApiModel("BbcStockAddressDTO.IdentifyAddressDTO")
    public static class IdentifyAddressDTO implements Serializable{

        @ApiModelProperty("地址")
        @NotBlank
        private String address;
    }

}
