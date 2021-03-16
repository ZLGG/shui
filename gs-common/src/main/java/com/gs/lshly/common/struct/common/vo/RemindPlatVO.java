package com.gs.lshly.common.struct.common.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author xxfc
* @since 2021-02-05
*/
public abstract class RemindPlatVO implements Serializable {

    @Data
    @ApiModel("RemindPlatVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("消息提醒")
        private String id;

        @ApiModelProperty("消息提醒内容")
        private String content;

        @ApiModelProperty("消息提醒业务类型")
        private Integer cType;

        @ApiModelProperty("消息提醒方式[10=站内信 20=微信]")
        private Integer cStyle;

        @ApiModelProperty("接受者ID")
        private String acceptId;

        @ApiModelProperty("触发者ID")
        private String triggerId;

        @ApiModelProperty("触发业务ID")
        private String triggerSid;

        @ApiModelProperty("触发者类型[10=会员 20=商家]")
        private Integer triggerType;

    }

    @Data
    @ApiModel("RemindPlatVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
