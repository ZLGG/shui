package com.gs.lshly.common.struct.merchadmin.pc.foundation.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class PCMerchFeedbackDTO implements Serializable {

    @Data
    @ApiModel("PCMerchFeedbackDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("姓名")
        private String fbOperatorName;

        @ApiModelProperty("邮箱")
        private String fbEmail;

        @ApiModelProperty("电话")
        private String fbContact;

        @ApiModelProperty("反馈内容")
        private String fbContent;

    }

    @Data
    @ApiModel("PCMerchFeedbackDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "意见反馈id")
        private String id;
    }


}
