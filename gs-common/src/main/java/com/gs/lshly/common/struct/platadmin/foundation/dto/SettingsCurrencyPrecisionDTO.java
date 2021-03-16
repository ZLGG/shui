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
public abstract class SettingsCurrencyPrecisionDTO implements Serializable {

    @Data
    @ApiModel("SettingsCurrencyPrecisionDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("保留位数")
        private Integer digit;

        @ApiModelProperty("取整方式")
        private String ways;
    }

    @Data
    @ApiModel("SettingsCurrencyPrecisionDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
