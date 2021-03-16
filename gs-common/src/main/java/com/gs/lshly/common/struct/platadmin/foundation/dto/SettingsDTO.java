package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsDTO implements Serializable {

    @Data
    @ApiModel("SettingsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("支付密码停用时间")
        private Integer pwdStopTime;

        @ApiModelProperty("支付密码停用错误次数")
        private Integer pwdStopError;

        @ApiModelProperty("支付密码停用提示次数")
        private Integer pwdStopTips;

        @ApiModelProperty("会员登录日志保留天数")
        private Integer userLogDays;

        @ApiModelProperty("多账号类型注册[10=是 20=否]")
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
    @ApiModel("SettingsDTO.GetPayETO")
    @Accessors(chain = true)
    public static class GetPayETO extends BaseDTO {

        @ApiModelProperty("是否开启货到付款[10=是 20=否]")
        private Integer isGetPay;

    }
    @Data
    @ApiModel("SettingsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("SettingsDTO.RightsSetting")
    @Accessors(chain = true)
    public static class RightsSetting extends BaseDTO {

        @ApiModelProperty("是否开启售后时间限制[10=是 20=否]")
        private Integer isRightsTimeLimit;

        @ApiModelProperty("售后类型[10=退货 20-退款]")
        private Integer rightsType;

        @ApiModelProperty("天数")
        private Integer RightsDays;


    }
    @Data
    @ApiModel("SettingsDTO.ActivityStartRemind")
    @Accessors(chain = true)
    public static class ActivityStartRemind extends BaseDTO {


        @ApiModelProperty("条数")
        private Integer messageCount;

    }


}
