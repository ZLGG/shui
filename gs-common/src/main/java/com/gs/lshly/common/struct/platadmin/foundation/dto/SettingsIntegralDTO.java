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
public abstract class SettingsIntegralDTO implements Serializable {

    @Data
    @ApiModel("SettingsIntegralDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("积分换算率")
        private Float rate;

        @ApiModelProperty("积分过期月份")
        private Integer monthToExpire;

        @ApiModelProperty("是否开启注册送积分[10=是 20=否]")
        private Integer isRegInteg;

        @ApiModelProperty("是否开启积分抵扣[10=是 20=否]")
        private Integer isIntegDeduct;
    }

    @Data
    @ApiModel("SettingsIntegralDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
