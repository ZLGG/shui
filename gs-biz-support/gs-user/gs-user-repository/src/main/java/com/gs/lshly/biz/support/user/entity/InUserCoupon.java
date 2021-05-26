package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/3/30 9:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_in_vip_coupon")
public class InUserCoupon extends Model {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 运营配置优惠券id
     */
    private Long couponId;

    /**
     * in会员userId
     */
    private String userId;

    /**
     * 优惠券使用开始时间
     */
    private LocalDate startTime;

    /**
     * 优惠券使用结束时间
     */
    private LocalDate endTime;

    /**
     * 优惠券抵扣金额
     */
    private BigDecimal couponPrice;

    /**
     * 使用门槛
     */
    private BigDecimal minPrice;

    /**
     * 优惠券类型（0-成为会员赠送 1-会员每月分享赠送
     */
    private Integer couponType;

    /**
     * 优惠券状态（0-未使用 1-已使用 2-已过期）
     */
    private Integer couponStatus;

    /**
     * 优惠券说明
     */
    @ApiModelProperty("优惠券说明")
    private String couponDesc;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;
}
