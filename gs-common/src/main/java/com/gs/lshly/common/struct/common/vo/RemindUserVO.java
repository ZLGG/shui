package com.gs.lshly.common.struct.common.vo;
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
public abstract class RemindUserVO implements Serializable {

    @Data
    @ApiModel("RemindUserVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("提醒ID")
        private String id;

        @ApiModelProperty("提醒内容")
        private String content;

        @ApiModelProperty("提醒业务类型")
        private Integer cType;

        @ApiModelProperty("提醒方式[10=站内信 20=微信]")
        private Integer cStyle;

        @ApiModelProperty("触发者业务ID")
        private String triggerSid;

    }

    @Data
    @ApiModel("RemindUserVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
