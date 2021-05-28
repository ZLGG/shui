package com.gs.lshly.biz.support.trade.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author chenyang
 * @since 2021-05-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_coupon")
public class Coupon extends Model {

    /**
     * 优惠券id
     */
    @TableId(value = "coupon_id")
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
     * 审核状态（0-待审核 1-已审核）
     */
    private Integer auditStatus;

    /**
     * 优惠券标题
     */
    private String couponName;

    /**
     * 优惠券描述
     */
    private String couponDesc;

    /**
     * 券标签 1-购买所得 2-分享所得
     */
    private Integer couponLabel;

    /**
     * 1-固定时间 2-领取后生效
     */
    private Integer useTime;

    /**
     * 优惠券使用开始时间
     */
    private Date startTime;

    /**
     * 优惠券使用结束时间
     */
    private Date endTime;

    /**
     * 领券后几天生效
     */
    private Integer afterDate;

    /**
     * 生效天数
     */
    private Integer effectiveDate;

    /**
     * 库存数量限制 1-数量限制 0-无限制
     */
    private Integer stockType;

    /**
     * 库存数
     */
    private Integer stockNum;

    /**
     * 减免金额
     */
    private BigDecimal deductionAmount;

    /**
     * 使用门槛
     */
    private BigDecimal useThreshold;

    /**
     * 下单可用抵扣 1-in会员权益 2-优惠券
     */
    private Integer useType;

    /**
     * 发放渠道 1-店铺直接领取 2-活动发券 3-商家发券
     */
    private Integer channel;

    /**
     * 每人限领
     */
    private Integer limited;

    /**
     * 优惠券说明
     */
    private String couponDescription;

    /**
     * 使用说明
     */
    private String instructions;

    /**
     * 选择维度 1-专区 2-类目 3-商品
     */
    private Integer level;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private Boolean flag;


}
