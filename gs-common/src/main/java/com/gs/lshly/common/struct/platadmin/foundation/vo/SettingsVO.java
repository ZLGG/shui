package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsVO implements Serializable {

    @Data
    @ApiModel("SettingsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("支付密码停用时间")
        private Integer pwdStopTime;


        @ApiModelProperty("支付密码停用错误次数")
        private Integer pwdStopError;


        @ApiModelProperty("支付密码停用提示次数")
        private Integer pwdStopTips;


        @ApiModelProperty("会员登录日志保留天数")
        private Integer userLogDays;


        @ApiModelProperty("多账号类型注册")
        private Integer regAccountType;


        @ApiModelProperty("商家操作日志保存天数")
        private Integer mercOperLogDays;


        @ApiModelProperty("平台操作日志保存天数")
        private Integer platOperLogDays;


        @ApiModelProperty("短信日志保存天数")
        private Integer messageLogDays;


        @ApiModelProperty("商家登录日志保存天数")
        private Integer mercRegLogDays;


        @ApiModelProperty("评价追评时间限制天数")
        private Integer addAssessDays;


        @ApiModelProperty("是否开启货到付款[10=是 20=否]")
        private Integer isCod;


        @ApiModelProperty("是否开启商品上架审核[10=是 20=否]")
        private Integer isReviewProduct;


        @ApiModelProperty("是否开启商家营销活动审核[10=是 20=否]")
        private Integer isReviewMarketing;

        @ApiModelProperty("是否开启商家文章平台审核[10=是 20=否]")
        private Integer isReviewArticle;
    }

    @Data
    @ApiModel("SettingsVO.GetPayVO")
    @Accessors(chain = true)
    public static class GetPayVO implements Serializable {

        @ApiModelProperty("是否开启货到付款[10=是 20=否]")
        private Integer isGetPay;

    }
    @Data
    @ApiModel("SettingsVO.Rights")
    @Accessors(chain = true)
    public static class Rights implements Serializable {


        @ApiModelProperty("是否开启退货时间限制[10=是 20=否]")
        private Integer isRefundTimeLimit;

        @ApiModelProperty(" 退货限制天数")
        private Integer refundDays;

        @ApiModelProperty("是否开启换货时间限制[10=是 20=否]")
        private Integer isReturnTimeLimit;

        @ApiModelProperty("换货限制天数")
        private Integer returnDays;

        /**
         * 是否开启待付款时间限制[10=是 20=否]
         */
        @ApiModelProperty("是否开启待付款时间限制[10=是 20=否]")
        private Integer isWaitTimeLimit;

        /**
         * 待付款限制天数
         */
        @ApiModelProperty("待付款限制天数")
        private Integer waitDays;

        /**
         * 是否开启自动收获时间限制[10=是 20=否]
         */
        @ApiModelProperty("是否开启自动收获时间限制[10=是 20=否]")
        private Integer isAutoTimeLimit;

        /**
         * 自动收货限制天数
         */
        @ApiModelProperty("自动收货限制天数")
        private Integer autoDays;

        @ApiModelProperty("活动开售提醒短线上限条数")
        private Integer  activityMessageCount;

    }


}
