package com.gs.lshly.common.struct.common.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public abstract class RemindMerchantVO implements Serializable {

    @Data
    @ApiModel("RemindMerchantVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("提醒ID")
        private String id;

        @ApiModelProperty("提醒内容")
        private String content;

        @ApiModelProperty("提醒业务类型")
        private Integer cType;

        @ApiModelProperty("触发业务ID")
        private String triggerSid;

        @ApiModelProperty("时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

//        @ApiModelProperty("提醒方式[10=站内信 20=微信]")
//        private Integer cStyle;
//
//        @ApiModelProperty("接受者ID")
//        private String accetId;
//
//        @ApiModelProperty("触发者ID")
//        private String triggerId;

//        @ApiModelProperty("触发者类型[10=平台 20=会员]")
//        private Integer triggerType;

    }

    @Data
    @ApiModel("RemindMerchantVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
