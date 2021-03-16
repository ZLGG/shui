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
public abstract class SettingsTradeDTO implements Serializable {

    @Data
    @ApiModel("SettingsTradeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("交易关闭，自动取消订单的时间间隔")
        private Integer cancelTime;

        @ApiModelProperty("交易完成，自动确认收货时间间隔")
        private Integer confirmTime;
    }

    @Data
    @ApiModel("SettingsTradeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
