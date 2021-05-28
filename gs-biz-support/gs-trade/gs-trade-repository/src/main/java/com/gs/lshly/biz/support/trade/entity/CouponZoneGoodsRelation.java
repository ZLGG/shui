package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券专区黑名单商品关联表
 * </p>
 *
 * @author chenyang
 * @since 2021-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_coupon_zone_goods_relation")
public class CouponZoneGoodsRelation extends Model {


    /**
     * id
     */
    private String id;

    /**
     * 优惠券id
     */
    private String couponId;

    /**
     * relationId
     */
    private String relationId;

    /**
     * 专区id
     */
    private String zoneId;

    /**
     * 商品id  
     */
    private String goodId;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 删除标记
     */
    private Boolean flag;


}
