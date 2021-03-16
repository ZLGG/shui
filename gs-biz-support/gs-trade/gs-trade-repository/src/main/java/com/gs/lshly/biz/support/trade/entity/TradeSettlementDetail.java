package com.gs.lshly.biz.support.trade.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.time.LocalDateTime;

/**
* <p>
* 
* </p>
*
* @author zst
* @since 2020-12-01
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_settlement_detail")
public class TradeSettlementDetail extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 结算明细ID
    */
    private String id;

    /**
    * 订单编号
    */
    private String tradeCode;

    /**
    * 结算汇总ID
    */
    private String settlementId;

    /**
    * 商家ID
    */
    private String shopId;

    /**
    * 所属商家
    */
    private String shopName;

    /**
    * 商品ID
    */
    private String goodsId;

    /**
    * 商品名称
    */
    private String goodsName;

    /**
    * 明细商品的编码(商品货号)
    */
    private String goodsNo;

    /**
    * 商品标题
    */
    private String goodsTitle;

    /**
    * 商品价格
    */
    private BigDecimal salePrice;

    /**
    * 购买数量
    */
    private Integer quantity;

    /**
    * 优惠分摊
    */
    private BigDecimal apportionDiscounts;

    /**
    * 商品款(不包含运费)
    */
    @TableField("Commodity_money")
    private BigDecimal commodityMoney;

    /**
    * 运费
    */
    private BigDecimal deliveryAmount;

    /**
     * 退款方式
     */
    private Integer refundWay;

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
    * 结算类型
    */
    private Integer settlementType;

    /**
    * 子订单级订单优惠金额	
    */
    private BigDecimal apportionSon;

    /**
    * 可结算时间
    */
    private LocalDateTime settleTime;

    /**
    * 付款时间
    */
    private LocalDateTime payTime;

    /*
    * 付款方式
    */
    private Integer payType;

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
