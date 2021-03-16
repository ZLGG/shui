package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsCurrencyVO implements Serializable {

    @Data
    @ApiModel("SettingsCurrencyVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("货币名")
        private String name;

        @ApiModelProperty("货币符号")
        private String symbol;
    }

    @Data
    @ApiModel("SettingsCurrencyVO.StyleDetailVO")
    public static class StyleDetailVO implements Serializable {

        @ApiModelProperty("保留精度")
        private Integer digit;

        @ApiModelProperty("取整方式[10=四舍五入 20=向下取整 30=向上取整]")
        private Integer ways;
    }

}
