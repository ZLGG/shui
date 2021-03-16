package com.gs.lshly.common.struct.common.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2021-02-05
*/
public abstract class RemindUserDTO implements Serializable {

    @Data
    @ApiModel("RemindUserDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("提醒内容")
        private String content;

        /**
         * 订单发货提醒  = 10
         * 订单取消提醒  = 11
         * 物流状态通知（配送中、配送完成） = 12
         * 活动通知（平台） = 13
         */
        @ApiModelProperty("提醒业务类型")
        private Integer cType;

        @ApiModelProperty("提醒方式[10=站内信 20=微信]")
        private Integer cStyle;

        @ApiModelProperty("接受者ID")
        private String accetId;

        @ApiModelProperty("触发者ID")
        private String triggerId;

        @ApiModelProperty("触发者业务ID")
        private String triggerSid;

        @ApiModelProperty("触发者类型[10=平台 20=商家]")
        private String triggerType;
    }

    @Data
    @ApiModel("RemindUserDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "提醒ID")
        private String id;
    }


}
