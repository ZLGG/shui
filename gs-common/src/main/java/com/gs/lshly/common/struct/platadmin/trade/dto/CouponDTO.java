package com.gs.lshly.common.struct.platadmin.trade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券 DTO
 * @author chenyang
 */
public class CouponDTO implements Serializable {

    @Data
    @ApiModel("CouponDTO.SaveCouponDTO")
    public static class SaveCouponDTO implements Serializable {

        @ApiModelProperty(value = "优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
        private Integer couponType;

        @ApiModelProperty(value = "优惠券状态（0-未使用 1-已使用 2-已过期）")
        private Integer couponStatus;

        @ApiModelProperty(value = "优惠券标题")
        private String couponName;

        @ApiModelProperty(value = "优惠券描述")
        private String couponDesc;

        @ApiModelProperty(value = "1-固定时间 2-领取后生效")
        private Integer useTime;

        @ApiModelProperty(value = "优惠券使用开始时间")
        private Date startTime;

        @ApiModelProperty(value = "优惠券使用结束时间")
        private Date endTime;

        @ApiModelProperty(value = "领券后几天生效")
        private Integer afterDate;

        @ApiModelProperty(value = "生效天数")
        private Integer effectiveDate;

        @ApiModelProperty(value = "库存数")
        private Integer stockNum;

        @ApiModelProperty(value = "减免金额")
        private BigDecimal deductionAmount;

        @ApiModelProperty(value = "使用门槛")
        private BigDecimal useThreshold;

        @ApiModelProperty(value = "下单可用抵扣 1-in会员权益 2-优惠券")
        private Integer useType;

        @ApiModelProperty(value = "发放渠道 1-店铺直接领取 2-活动发券 3-商家发券")
        private Integer channel;

        @ApiModelProperty(value = "每人限领")
        private Integer limited;

        @ApiModelProperty(value = "优惠券说明")
        private String couponDescription;

        @ApiModelProperty(value = "使用说明")
        private String instructions;

        @ApiModelProperty(value = "是否适用全部商品")
        private Boolean isAllGoods;

        @ApiModelProperty(value = "是否选择参加活动的商品")
        private Boolean isActivity;

        @ApiModelProperty(value = "适用商品ids")
        private List<String> goodIds;
    }

    @Data
    @ApiModel("CouponDTO.UpdateCouponDTO")
    public static class UpdateCouponDTO implements Serializable {

        @ApiModelProperty(value = "优惠券id")
        private Long couponId;

        @ApiModelProperty(value = "优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
        private Integer couponType;

        @ApiModelProperty(value = "优惠券状态（0-未使用 1-已使用 2-已过期）")
        private Integer couponStatus;

        @ApiModelProperty(value = "优惠券标题")
        private String couponName;

        @ApiModelProperty(value = "优惠券描述")
        private String couponDesc;

        @ApiModelProperty(value = "1-固定时间 2-领取后生效")
        private Integer useTime;

        @ApiModelProperty(value = "优惠券使用开始时间")
        private Date startTime;

        @ApiModelProperty(value = "优惠券使用结束时间")
        private Date endTime;

        @ApiModelProperty(value = "领券后几天生效")
        private Integer afterDate;

        @ApiModelProperty(value = "生效天数")
        private Integer effectiveDate;

        @ApiModelProperty(value = "库存数")
        private Integer stockNum;

        @ApiModelProperty(value = "减免金额")
        private BigDecimal deductionAmount;

        @ApiModelProperty(value = "使用门槛")
        private BigDecimal useThreshold;

        @ApiModelProperty(value = "下单可用抵扣 1-in会员权益 2-优惠券")
        private Integer useType;

        @ApiModelProperty(value = "发放渠道 1-店铺直接领取 2-活动发券 3-商家发券")
        private Integer channel;

        @ApiModelProperty(value = "每人限领")
        private Integer limited;

        @ApiModelProperty(value = "优惠券说明")
        private String couponDescription;

        @ApiModelProperty(value = "使用说明")
        private String instructions;

        @ApiModelProperty(value = "是否适用全部商品")
        private Boolean isAllGoods;

        @ApiModelProperty(value = "是否选择参加活动的商品")
        private Boolean isActivity;

        @ApiModelProperty(value = "适用商品ids")
        private List<String> goodIds;
    }

}
