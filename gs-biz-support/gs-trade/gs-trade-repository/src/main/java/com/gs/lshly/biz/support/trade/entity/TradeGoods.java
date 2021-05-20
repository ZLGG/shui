package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2020-10-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_goods")
public class TradeGoods extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 交易商品ID
     */
    private String id;

    /**
     * 交易ID
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
     * 店铺名称
     */
    private String shopName;

    /**
     * 商家ID
     */
    private String merchantId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品货号
     */
    private String goodsNo;

    /**
     * SKU ID
     */
    private String skuId;

    /**
     * 格规值
     */
    private String skuSpecValue;

    /**
     * SKU IMG
     */
    private String skuImg;

    /**
     * SKU 商品货号
     */
    private String skuGoodsNo;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 销售价
     */
    private BigDecimal salePrice;

    /**
     * 优惠金额
     */
    private BigDecimal discountAmount;

    /**
     * 支付总金额
     */
    private BigDecimal payAmount;
    
    /**
     * 支付总金额
     */
    private BigDecimal tradePointAmount;

    /**
     * 所得积分
     */
    private BigDecimal giftIntegral;

    /**
     * 交易总价占比
     */
    private BigDecimal tradeAmountPercent;

    /**
     * 是否允许评论
     */
    private Integer commentFlag;

    /**
     * 优惠券类型（普通、IN会员）
     */
    private Integer couponType;

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
