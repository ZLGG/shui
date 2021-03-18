package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 信天游商品日志表
 * </p>
 *
 * @author yingjun
 * @since 2021-03-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_trade_goods_travelsky")
public class TradeGoodsTravelsky extends Model  {

    private static final long serialVersionUID = 1L;

    /**
     * 交易商品ID
     */
    private String id;

    /**
     * 交易商品ID
     */
    private String tradeGoodsId;

    /**
     * 商品ID
     */
    private String goodsId;

    private String tradeId;

    private Integer thirdProductId;

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

    private String status;

    private String resultMsg;

    private String orderId;

    private String productCode;

    private String validDate;

    private String smsMsg;

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
