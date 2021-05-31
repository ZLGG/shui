package com.gs.lshly.common.struct.bbc.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/3/18 10:04
 */
public class BbcInUserCouponVO implements Serializable {

    @ApiModel("BbcInUserCouponVO.ListVO")
    @Data
    public static class ListVO implements Serializable {
        @ApiModelProperty("优惠券id")
        private String id;

        @ApiModelProperty("运营配置优惠券id")
        private String couponId;

        @ApiModelProperty("距离到期天数")
        private Integer expireDays;

        @ApiModelProperty("优惠券使用开始时间")
        @JsonIgnore
        private LocalDate startTime;

        @ApiModelProperty("优惠券使用结束时间")
        @JsonIgnore
        private LocalDate endTime;

        @ApiModelProperty("优惠券抵扣金额")
        private BigDecimal couponPrice;

        @ApiModelProperty("使用门槛")
        private BigDecimal minPrice;

        @ApiModelProperty("优惠券说明")
        private String couponDesc;

        @ApiModelProperty("优惠券名称")
        private String couponName;

        @ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
        private Integer couponType;

    }

    @ApiModel("BbcInUserCouponVO.CardList")
    @Data
    public static class CardList implements Serializable {
        @ApiModelProperty("有效日期")
        @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
        private Date endTime;

        @ApiModelProperty("会员卡类型（1-月会员，2-年会员）")
        private Integer type;
    }

    @ApiModel("BbcInUserCouponVO.Coupon")
    @Data
    public static class Coupon implements Serializable {
        private String couponId;

        /**
         * 优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）
         */
        private Integer couponType;

        /**
         * 优惠券状态（0-未使用 1-已使用 2-已过期）
         */
        private Integer couponStatus;

        /**
         * 优惠券标题
         */
        private String couponName;

        /**
         * 优惠券描述
         */
        private String couponDesc;

        /**
         * 领券后几天生效
         */
        private Integer afterDate;

        /**
         * 生效天数
         */
        private Integer effectiveDate;

        /**
         * 减免金额
         */
        private BigDecimal deductionAmount;

        /**
         * 使用门槛
         */
        private BigDecimal useThreshold;

        /**
         * 优惠券说明
         */
        private String couponDescription;

    }

    @ApiModel("BbcInUserCouponVO.MyCouponListVO")
    @Data
    public static class MyCouponListVO implements Serializable {
        @ApiModelProperty("优惠券id")
        private String id;

        @ApiModelProperty("优惠券使用开始时间")
        private LocalDate startTime;

        @ApiModelProperty("优惠券使用结束时间")
        private LocalDate endTime;

        @ApiModelProperty("优惠券抵扣金额")
        private BigDecimal couponPrice;

        @ApiModelProperty("使用门槛")
        private BigDecimal minPrice;

        @ApiModelProperty("优惠券说明")
        private String couponDesc;

        @ApiModelProperty("优惠券名称")
        private String couponName;

        @ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
        private Integer couponType;
    }

    @ApiModel("BbcInUserCouponVO.GoodsCouponListVO")
    @Data
    public static class GoodsCouponListVO implements Serializable {
        @ApiModelProperty("优惠券id")
        private String id;

        @ApiModelProperty("优惠券使用开始时间")
        private LocalDate startTime;

        @ApiModelProperty("优惠券使用结束时间")
        private LocalDate endTime;

        @ApiModelProperty("优惠券减免金额")
        private BigDecimal deductionAmount;

        @ApiModelProperty("优惠券减免积分")
        private Integer deductionPoints;

        @ApiModelProperty("使用门槛")
        private BigDecimal minPrice;

        @ApiModelProperty("优惠券说明")
        private String couponDesc;

        @ApiModelProperty("优惠券名称")
        private String couponName;

        @ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
        private Integer couponType;
    }

}

