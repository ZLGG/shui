package com.gs.lshly.common.struct.common.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class RemindPlatDTO implements Serializable {


    /**
     * 企业会员待审核  = 10
     * 商家入驻待审核  = 11
     * 商品上架待审核  = 12
     * 申请类目待审核  = 13
     * 商家文章待审核  = 14
     * 商家营销活动待审核 = 15
     * 商家报名活动待审核 = 16
     * 评论申诉待审核 = 17
     * 订单投诉待审核 = 18
     * 售后申请待审核 = 19
     * 退款待审核 = 20
     * 商家意见反馈待审核 = 21
     * 会员意见反馈提醒 = 22
     * 转账待审核 = 23
     */


    @Data
    @ApiModel("RemindPlatDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("提醒内容")
        private String content;

        @ApiModelProperty("提醒业务类型[10=站内信 20=微信消息]")
        private Integer cType;

        @ApiModelProperty("接受者ID")
        private String acceptId;

        @ApiModelProperty("触发者ID")
        private String triggerId;

        @ApiModelProperty("触发业务ID")
        private String triggerSid;
    }

    @Data
    @ApiModel("RemindPlatDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }

    @Data
    @ApiModel("RemindPlatDTO.JustDTO")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class JustDTO extends BaseDTO {

        @ApiModelProperty("触发者ID")
        private String triggerId;

        @ApiModelProperty("触发业务ID")
        private String triggerSid;
    }

}
