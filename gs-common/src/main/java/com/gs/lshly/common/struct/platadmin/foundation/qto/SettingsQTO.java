package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsQTO implements Serializable {

    @Data
    @ApiModel("SettingsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("支付密码停用时间")
        private Integer pwdStopTime;

        @ApiModelProperty("支付密码停用错误次数")
        private Integer pwdStopError;

        @ApiModelProperty("支付密码停用提示次数")
        private Integer pwdStopTips;

        @ApiModelProperty("会员登录日志保留天数")
        private Integer userLogDays;

        @ApiModelProperty("多账号类型注册")
        private String regAccountType;

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
    }
}
