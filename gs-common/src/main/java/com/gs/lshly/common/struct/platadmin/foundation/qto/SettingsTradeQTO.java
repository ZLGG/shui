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
public abstract class SettingsTradeQTO implements Serializable {

    @Data
    @ApiModel("SettingsTradeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("交易关闭，自动取消订单的时间间隔")
        private Integer cancelTime;

        @ApiModelProperty("交易完成，自动确认收货时间间隔")
        private Integer confirmTime;
    }
}
