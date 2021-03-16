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
* 
* </p>
*
* @author oy
* @since 2020-11-04
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_pay_offline")
public class TradePayOffline extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 线下支付记录表ID
    */
    private String id;

    /**
     * 交易id
     */
    private String tradeId;

    /**
     * 用户名
     */
    private String userName;

    /**
    * 支付ID
    */
    private String payId;

    /**
    * 支付凭证
    */
    private String payOrder;

    /**
    * 转帐金额
    */
    private BigDecimal payAmount;

    /**
    * 账户名
    */
    private String accountName;

    /**
    * 银行账号
    */
    private String accountNumber;

    /**
    * 银行名称
    */
    private String bankName;

    /**
    * 转帐备注
    */
    private String payRemark;

    /**
    * 审核状态:待确认/驳回/确认
    */
    private Integer verifyState;

    /**
    * 审核人名称
    */
    private String verifyName;

    /**
    * 审核备注
    */
    private String verifyRemark;

    /**
     * 审核附件
     */
    private String verifyAttach;

    /**
     * 凭证图片
     */
    private String certificatePicture;

    /**
    * 审核人ID
    */
    private String merchantAccountId;

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


}
