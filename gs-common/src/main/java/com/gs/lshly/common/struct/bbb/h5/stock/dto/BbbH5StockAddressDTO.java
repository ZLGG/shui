package com.gs.lshly.common.struct.bbb.h5.stock.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbb.pc.stock.dto.BbbStockAddressDTO;
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
public abstract class BbbH5StockAddressDTO implements Serializable {

    @Data
    @ApiModel("BbbH5StockAddressDTO.ETO")
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

        @ApiModelProperty("联系人")
        private String contactsName;

        @ApiModelProperty("手机号")
        private String contactsPhone;

    }

    @Data
    @ApiModel("BbbH5StockAddressDTO.DetailETO")
    public static class DetailETO extends ETO {

        @ApiModelProperty("是否设置成默认地址(1 =是 ，0 =否")
        private Integer isDefault;

        @ApiModelProperty("地址类型[10=收货 20=发票 30=发货 40=退货]")
        private Integer addressType;

    }

    @Data
    @ApiModel("BbbH5StockAddressDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;

    }

    @Data
    @ApiModel("BbbH5StockAddressDTO.IdAndTypeDTO")
    public static class IdAndTypeDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;

        @ApiModelProperty("地址类型[10=收货 20=发票 30=发货 40=退货]")
        private Integer addressType;

    }


}
