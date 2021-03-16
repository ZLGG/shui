package com.gs.lshly.common.struct.bbb.pc.user.dto;

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
public abstract class BbbDataFeedbackDTO implements Serializable {

    @Data
    @ApiModel("BbcDataFeedbackDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "意见反馈id",hidden = true)
        private String id;

        @ApiModelProperty("反馈类型(10-功能异常,20-优化建议,30-其他)")
        private Integer fbType;

        @ApiModelProperty("反馈内容")
        private String fbContent;

        @ApiModelProperty("联系方式")
        private String fbContact;

    }

    @Data
    @ApiModel("BbcDataFeedbackDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "意见反馈id")
        private String id;
    }


}
