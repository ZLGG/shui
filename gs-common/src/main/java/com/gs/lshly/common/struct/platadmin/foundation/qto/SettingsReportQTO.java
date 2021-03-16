package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsReportQTO implements Serializable {

    @Data
    @ApiModel("SettingsReportQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("是否开启签到[10=是 20=否]")
        private Integer isOpenState;

        @ApiModelProperty("是否开启签到送积分[10=是 20=否]")
        private Integer isReportInteg;

        @ApiModelProperty("签到送积分")
        private Integer reportInteg;
    }
}
