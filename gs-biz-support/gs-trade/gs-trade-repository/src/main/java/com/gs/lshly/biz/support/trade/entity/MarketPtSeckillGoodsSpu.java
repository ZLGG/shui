package com.gs.lshly.biz.support.trade.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;

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
public class MarketPtSeckillGoodsSpu implements Serializable {

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
    private String goodsType;

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
     * 品牌id
     */
    private String brandId;

    /**
     * 类目id
     */
    private String categoryId;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 逻辑删除标记
     */
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
