package com.gs.lshly.biz.support.trade.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商家报名商品(sku)
 * </p>
 *
 * @author yingjun
 * @since 2021-05-07
 */
@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_market_pt_seckill_goods_sku")
public class MarketPtSeckillGoodsSku extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 活动ID
     */
    private String activityId;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 商家ID
     */
    private String merchantId;

    private String skuId;

    /**
     * 商家报名商品SPU项ID
     */
    private String goodsSpuItemId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * sku活动价
     */
    private BigDecimal activitySaleSkuPrice;

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
