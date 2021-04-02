package com.gs.lshly.common.struct.common.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2021-02-05
*/
public abstract class RemindMerchantDTO implements Serializable {

    @Data
    @ApiModel("RemindMerchantDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("提醒内容")
        private String content;

        /**
         * 平台通知              = 10
         * 待发货提醒            = 11
         * 用户催单提醒          = 12
         * 取消订单待审核        = 13
         * 活动报名提醒          = 14
         * 退换货待审核          = 15
         * 咨询待回复提醒        = 16
         */
        @ApiModelProperty("提醒业务类型[类型多请参照外部文档]")
        private Integer cType;

        @ApiModelProperty("提醒方式[10=站内信 20=微信]")
        private Integer cStyle;

        @ApiModelProperty("接受者ID")
        private String accetId;

        @ApiModelProperty("触发者ID")
        private String triggerId;

        @ApiModelProperty("触发业务ID")
        private String triggerSid;

        @ApiModelProperty("触发者类型[10=平台 20=会员]")
        private String triggerType;
    }

    @Data
    @ApiModel("RemindMerchantDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "提醒ID")
        private String id;
    }


    @Data
    @ApiModel("RemindMerchantDTO.JustDTO")
    @AllArgsConstructor
    public static class JustDTO extends BaseDTO {

        @ApiModelProperty("接受者ID....[店铺ID]")
        private String accetId;

        @ApiModelProperty("触发者ID....[触发者会员ID]")
        private String triggerId;

        @ApiModelProperty("触发业务ID....[相应业务ID，如订单待审=订单ID,商家入驻待审 = 商家入驻ID]")
        private String triggerSid;

    }

    @Data
    @ApiModel("RemindMerchantDTO.NoticAcctAllDTO")
    @AllArgsConstructor
    public static class NoticAcctAllDTO extends BaseDTO {

        @ApiModelProperty("接受者ID....店铺ID")
        private String accetId;

        @ApiModelProperty("触发者ID....[触发者会员ID]")
        private String triggerId;

        @ApiModelProperty("触发业务ID....[相应业务ID，如订单待审=订单ID,商家入驻待审 = 商家入驻ID]")
        private String triggerSid;

        @ApiModelProperty("消息内容")
        private String content;

    }

    @Data
    @ApiModel("RemindMerchantDTO.NoticAcctDTO")
    @AllArgsConstructor
    public static class NoticAcctDTO extends BaseDTO {

        @ApiModelProperty("接受者ID....[店铺ID]")
        private List<String> accetIdList;

        @ApiModelProperty("触发者ID....[触发者会员ID]")
        private String triggerId;

        @ApiModelProperty("触发业务ID....[相应业务ID，如订单待审=订单ID,商家入驻待审 = 商家入驻ID]")
        private String triggerSid;

        @ApiModelProperty("消息内容")
        private String content;
    }
}
