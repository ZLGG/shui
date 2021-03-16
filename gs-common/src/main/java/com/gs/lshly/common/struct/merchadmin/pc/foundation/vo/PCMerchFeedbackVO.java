package com.gs.lshly.common.struct.merchadmin.pc.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class PCMerchFeedbackVO implements Serializable {

    @Data
    @ApiModel("PCMerchFeedbackVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

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
        private LocalDateTime fbOperatorTime;

        @ApiModelProperty("处理结果")
        private String fbResultContent;

        @ApiModelProperty("处理时间")
        private LocalDateTime fbResultTime;

    }

    @Data
    @ApiModel("PCMerchFeedbackVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
