package com.gs.lshly.biz.support.trade.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

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
 * 商家报名商品(spu)
 * </p>
 *
 * @author yingjun
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_market_pt_seckill_goods_spu")
public class MarketPtSeckillGoodsSpu extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;
    /**
     * 是否被选中
     */
    private Integer choose;

    /**
     * 秒杀id
     */
    private String seckillId;

    /**
     * 活动场次Id
     */
    private String timeQuantumId;
    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 商家ID
     */
    private String merchantId;

    /**
     * 商品类型
     */
    private Integer goodsType;

    private BigDecimal seckillSalePrice;

    /**
     * 秒杀名称
     */
    private String name;

    /**
     * 标签
     */
    private String label;

    /**
     * 描述
     */
    private String seckillDescribe;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 类目id
     */
    private String categoryId;

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
     * 积分金额
     */
    private BigDecimal seckillPointPrice;

    /**
     * IN会员秒杀价格
     */
    private BigDecimal seckillInMemberPointPrice;
}
