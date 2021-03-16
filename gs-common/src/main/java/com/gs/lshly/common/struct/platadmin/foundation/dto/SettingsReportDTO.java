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
public abstract class SettingsReportDTO implements Serializable {

    @Data
    @ApiModel("SettingsReportDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("是否开启签到[10=是 20=否]")
        private Integer isOpenState;

        @ApiModelProperty("是否开启签到送积分[10=是 20=否]")
        private Integer isReportInteg;

        @ApiModelProperty("签到送积分")
        private Integer reportInteg;
    }

    @Data
    @ApiModel("SettingsReportDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
