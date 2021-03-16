package com.gs.lshly.biz.support.trade.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.time.LocalDateTime;

/**
* <p>
* 
* </p>
*
* @author zst
* @since 2020-11-30
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_settlement")
public class TradeSettlement extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 结算汇总ID
    */
    private String id;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 所属商家
    */
    private String shopName;

    /**
    * 账单编号
    */
    private String tradeCode;

    /**
    * 订单数量
    */
    private Integer quantity;

    /**
    * 销售价
    */
    private BigDecimal salePrice;


    /**
    * 运费金额
    */
    private BigDecimal deliveryAmount;

    /**
    * 退款金额
    */
    private BigDecimal tradeAmount;

    /**
    * 佣金
    */
    private BigDecimal commission;

    /**
    * 结算金额
    */
    private BigDecimal settlementAmount;

    /**
    * 账单开始时间
    */
    private LocalDateTime billStartTime;

    /**
    * 账单结束时间
    */
    private LocalDateTime billEndTime;

    /**
    * 结算时间
    */
    private LocalDateTime settlementTime;

    /**
    * 结算状态
    */
    private Integer settlementState;

    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
    * 修改时间
    */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
    * 逻辑删除标记
    */
    private Boolean flag;

}
