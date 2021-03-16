package com.gs.lshly.common.struct.platadmin.foundation.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class DataFeedbackVO implements Serializable {

    @Data
    @ApiModel("DataFeedbackVO.ListVO")
    public static class ListVO  implements Serializable {

        @ApiModelProperty("意见反馈id")
        private String id;

        @ApiModelProperty("反馈类型(10-功能故障,20-产品建议,30-其他)")
        private Integer fbType;

        @ApiModelProperty("反馈内容")
        private String fbContent;

        @ApiModelProperty("邮箱")
        private String fbEmail;

        @ApiModelProperty("联系方式")
        private String fbContact;

        @ApiModelProperty("提交人姓名")
        private String fbOperatorName;

        @ApiModelProperty("提交时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime fbOperatorTime;

        @ApiModelProperty("是否处理[0=否 1=是]")
        private Integer fbHanderState;

        @ApiModelProperty("处理结果")
        private String fbResultContent;

        @ApiModelProperty("处理时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime fbResultTime;

    }

    @Data
    @ApiModel("DataFeedbackVO.DetailVO")
    public static class DetailVO extends ListVO {
    }
}
