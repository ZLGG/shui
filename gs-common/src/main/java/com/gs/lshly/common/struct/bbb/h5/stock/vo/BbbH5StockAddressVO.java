package com.gs.lshly.common.struct.bbb.h5.stock.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author xxfc
* @since 2020-11-02
*/
public abstract class BbbH5StockAddressVO implements Serializable {

    @Data
    @ApiModel("BbbH5StockAddressVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("省")
        private String province;

        @ApiModelProperty("省")
        private String provinceCode;

        @ApiModelProperty("市")
        private String city;

        @ApiModelProperty("市")
        private String cityCode;

        @ApiModelProperty("县/区")
        private String county;

        @ApiModelProperty("县/区")
        private String countyCode;

        @ApiModelProperty("经度")
        private BigDecimal longitude;

        @ApiModelProperty("街道")
        private String reals="";

        @ApiModelProperty("纬度")
        private BigDecimal latitude;

        @ApiModelProperty("详细地址")
        private String street="";

        @ApiModelProperty("联系人")
        private String contactsName;

        @ApiModelProperty("手机号")
        private String contactsPhone;

        @ApiModelProperty("默认收货地址[0=否 1=是]")
        private Integer isDefault;

        @ApiModelProperty("地址全路径")
        private String fullAddres;

        public String getFullAddres(){
            String streetO=StringUtils.isEmpty(street)?"":street;
            String realsO=StringUtils.isEmpty(reals)?"":reals;
            return province + city + county + streetO + realsO ;
        }

    }

    @Data
    @ApiModel("BbbH5StockAddressVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
