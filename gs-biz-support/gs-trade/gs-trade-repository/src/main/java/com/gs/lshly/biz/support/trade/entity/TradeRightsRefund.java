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
* 售后退款表
* </p>
*
* @author oy
* @since 2020-12-06
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_rights_refund")
public class TradeRightsRefund extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 售后退款表ID
    */
    private String id;

    /**
    * 售后表ID
    */
    private String rightsId;

    /**
    * 订单ID
    */
    private String tradeId;

    /**
    * 退款金额
    */
    private BigDecimal refundAmount;

    /**
    * 退款结果(10:成功,20:失败)
    */
    private Integer state;

    /**
     * 操作人
     */
    private String operationName;
    /**
    * 退款结果信息
    */
    private String resultInfo;
    /**
     * 退款银行名字
     */
    private String refundBankName;
    /**
     * 退款银行账户
     */
    private String refundAccount;
    /**
     * 退款人
     */
    private String refundName;
    /**
     * 收款银行名字
     */
    private String collectBankName;
    /**
     * 收款银行账户
     */
    private String collectAccount;
    /**
     * 收款人
     */
    private String collectName;
    /**
     * 退款方式[10=线下退款 20=原路返回]
     */
    private Integer refundType;


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
