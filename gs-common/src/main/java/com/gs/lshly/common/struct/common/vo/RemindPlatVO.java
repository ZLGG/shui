package com.gs.lshly.common.struct.common.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

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

        @ApiModelProperty("消息提醒业务类型[参照外部文档]")
        private Integer cType;

        @ApiModelProperty("触发业务ID")
        private String triggerSid;

        @ApiModelProperty("消息时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

    }

    @Data
    @ApiModel("RemindPlatVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
