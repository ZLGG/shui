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
public abstract class SettingsCurrencyPrecisionQTO implements Serializable {

    @Data
    @ApiModel("SettingsCurrencyPrecisionQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("保留位数")
        private Integer digit;

        @ApiModelProperty("取整方式")
        private String ways;
    }
}
