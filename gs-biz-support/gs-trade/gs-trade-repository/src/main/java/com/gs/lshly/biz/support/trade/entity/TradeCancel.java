package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 交易订单取消表
 * </p>
 *
 * @author oy
 * @since 2020-11-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_cancel")
public class TradeCancel extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 订单ID
     */
    private String tradeId;

    /**
     * 订单Code
     */
    private String tradeCode;

    /**
     * 会员ID
     */
    private String userId;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 支付ID
     */
    private String payId;

    /**
     * 退款金额
     */
    private BigDecimal tradeAmount;

    /**
     * 提交申请:10,商家驳回:20,商家确认:30,退款:40,完成:50
     */
    private Integer cancelState;

    /**
     * 申请类型:用户取消订单:10,商家取消订单:20
     */
    private Integer applyType;

    /**
     * 申请时间
     */
    private LocalDateTime applyTime;

    /**
     * 原因类型:
     */
    private Integer reasonType;

    /**
     * 原因说明
     */
    private String remark;

    /**
     * 拒绝理由
     */
    private String rejectReason;

    /**
     * 拒绝时间
     */
    private LocalDateTime rejectTime;

    /**
     * 通过时间
     */
    private LocalDateTime passTime;

    /**
     * 退款状态:无需退款:10,等待审核:20,等待退款:30,退款成功:40
     */
    private Integer refundState;

    /**
     * 退款时间
     */
    private LocalDateTime refundTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 逻辑删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;

    /**
     * 退款积分
     */
    private Integer tradePoint;
}
