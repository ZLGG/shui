package com.gs.lshly.common.struct.bbb.h5.foundation.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author hyy
* @since 2020-11-16
*/
public abstract class BbbH5DataFeedbackDTO implements Serializable {

    @Data
    @ApiModel("BbcDataFeedbackDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "意见反馈id",hidden = true)
        private String id;

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

        @ApiModelProperty(value = "提交人类型(10-商家,20-会员)",hidden = true)
        private Integer fbOperatorType;

    }

    @Data
    @ApiModel("BbcDataFeedbackDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "意见反馈id")
        private String id;
    }


}
