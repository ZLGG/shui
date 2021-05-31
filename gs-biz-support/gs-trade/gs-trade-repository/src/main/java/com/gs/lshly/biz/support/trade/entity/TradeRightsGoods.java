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
* 售后商品表
* </p>
*
* @author oy
* @since 2020-12-06
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_rights_goods")
public class TradeRightsGoods extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 售后商品表ID
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
    * 订单商品ID
    */
    private String tradeGoodsId;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 订单编号
    */
    private String orderCode;

    /**
    * 商品名称
    */
    private String goodsName;

    /**
    * SKU ID
    */
    private String skuId;

    /**
    * 格规值
    */
    private String skuSpecValue;

    /**
    * 售后数量
    */
    private Integer quantity;

    /**
    * 销售价
    */
    private BigDecimal salePrice;

    /**
    * 退款金额
    */
    private BigDecimal refundAmount;

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
     * 原商品或换货商品(10:原商品,20:换货商品)
     */
    private Integer goodsType;

    /**
     * 退款金额
     */
    private BigDecimal refundPoint;
}
