package com.gs.lshly.common.struct.platadmin.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CouponVO implements Serializable {

    @Data
    @ApiModel("CouponVO.CouponListVO")
    public static class CouponListVO implements Serializable {

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

        @ApiModelProperty(value = "库存数")
        private Integer stockNum;

        @ApiModelProperty(value = "发放渠道 1-店铺直接领取 2-活动发券 3-商家发券")
        private Integer channel;

        @ApiModelProperty(value = "审核状态（0-待审核 1-已审核）")
        private Integer auditStatus;
    }

    @Data
    @ApiModel("CouponVO.CouponDetailVO")
    public static class CouponDetailVO implements Serializable {

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

        @ApiModelProperty(value = "券标签 1-购买所得 2-分享所得")
        private Integer couponLabel;

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

        @ApiModelProperty(value = "库存数量限制 1-数量限制 0-无限制")
        private Integer stockType;

        @ApiModelProperty(value = "库存数")
        private Integer stockNum;

        @ApiModelProperty(value = "减免金额")
        private BigDecimal deductionAmount;

        @ApiModelProperty(value = "减免积分")
        private Integer deductionPoints;

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

        @ApiModelProperty(value = "使用商品维度 1-专区 2-类目 3-商品")
        private Integer level;

        @ApiModelProperty(value = "是否适用全部商品")
        private Boolean isAllGoods;

        @ApiModelProperty(value = "商品ids")
        private List<LevelVO> levelIds;

        @ApiModelProperty(value = "审核状态（0-待审核 1-已审核）")
        private Integer auditStatus;
    }

    @Data
    @ApiModel("CouponDTO.CouponGoodVO")
    public static class LevelVO implements Serializable {

        @ApiModelProperty(value = "专区黑名单商品ids")
        private List<String> excludeGoodIds;

        @ApiModelProperty(value = "专区id/类目id/商品id")
        private String levelId;
    }
}
