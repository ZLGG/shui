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
@TableName("gs_trade_pay")
public class TradePay extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 交易支付表ID
    */
    private String id;

    /**
    * 订单ID
    */
    private String tradeId;

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
     * 合并支付交易编号（传递给第三方支付的交易单号）
     */
    private String mergePaymentTradeCode;

    /**
    * 交易编号
    */
    private String tradeCode;

    /**
     * 预下单token
     */
    private String token;

    /**
    * 支付单号
    */
    private String payCode;

    /**
     * 支付信息
     */
    private String payInfo;

    /**
    * 支付方式:10=支付扫码,20=支付宝APP,30=微信扫码,40=微信公众号,50=微信APP支付,60=微信小程序支付,70=银联PC,80=银联移动,90=线下支付
    */
    private Integer payType;

    /**
    * 支付状态:10=待支付,20=已支付
    */
    private Integer payState;

    /**
    * 订单总金额
    */
    private BigDecimal totalAmount;

    /**
    * 客户端IP地址
    */
    private String clientIp;

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
    
    private String thirdCode;


}
