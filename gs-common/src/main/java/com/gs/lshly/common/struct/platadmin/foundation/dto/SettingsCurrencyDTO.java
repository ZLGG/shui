package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsCurrencyDTO implements Serializable {

    @Data
    @ApiModel("SettingsCurrencyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("货币名")
        private String name;

        @ApiModelProperty("货币符号")
        private String symbol;
    }

    @Data
    @ApiModel("SettingsCurrencyDTO.StyleETO")
    @Accessors(chain = true)
    public static class StyleETO extends BaseDTO {

        @ApiModelProperty("保留精度")
        private Integer digit;

        @ApiModelProperty("取整方式[10=四舍五入 20=向下取整 30=向上取整]")
        private Integer ways;

    }

    @Data
    @ApiModel("SettingsCurrencyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
