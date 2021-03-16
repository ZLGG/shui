package com.gs.lshly.common.struct.bbb.pc.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class BbbDataFeedbackQTO implements Serializable {

    @Data
    @ApiModel("BbcDataFeedbackQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("反馈类型(10-功能故障,20-产品建议,30-其他)")
        private Integer fbType;

        @ApiModelProperty("反馈内容")
        private String fbContent;

        @ApiModelProperty("邮箱")
        private String fbEmail;

        @ApiModelProperty("联系方式")
        private String fbContact;

        @ApiModelProperty("提交人id")
        private String fbOperatorId;

        @ApiModelProperty("提交人姓名")
        private String fbOperatorName;

        @ApiModelProperty("提交人类型(10-商家,20-会员)")
        private Integer fbOperatorType;

        @ApiModelProperty("提交时间")
        private LocalDateTime fbOperatorTime;

        @ApiModelProperty("处理结果")
        private String fbResultContent;

        @ApiModelProperty("处理时间")
        private LocalDateTime fbResultTime;
    }
}
