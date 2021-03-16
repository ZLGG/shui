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
public abstract class SettingsTradeVO implements Serializable {

    @Data
    @ApiModel("SettingsTradeVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("交易关闭，自动取消订单的时间间隔")
        private Integer cancelTime;


        @ApiModelProperty("交易完成，自动确认收货时间间隔")
        private Integer confirmTime;

    }
}
