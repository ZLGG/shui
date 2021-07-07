package com.gs.lshly.biz.support.user.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Author yangxi
 * @create 2021/3/30 9:47
 */
@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_coupon")
public class UserCoupon extends Model {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 运营配置优惠券id
     */
    private String couponId;

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
     * 优惠券类型（优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）
     */
    private Integer couponType;

    /**
     * 优惠券状态（0-未使用 1-已使用 2-已过期）
     */
    private Integer couponStatus;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 优惠券说明
     */
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
