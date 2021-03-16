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
public abstract class SettingsIntegralQTO implements Serializable {

    @Data
    @ApiModel("SettingsIntegralQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("积分换算率")
        private Float rate;

        @ApiModelProperty("积分过期月份")
        private Integer monthToExpire;

        @ApiModelProperty("是否开启注册送积分[10=是 20=否]")
        private Integer isRegInteg;

        @ApiModelProperty("是否开启积分抵扣[10=是 20=否]")
        private Integer isIntegDeduct;
    }
}
