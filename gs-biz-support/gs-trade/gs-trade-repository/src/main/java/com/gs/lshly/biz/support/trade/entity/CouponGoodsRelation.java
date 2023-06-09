package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 优惠券商品关联表
 * </p>
 *
 * @author chenyang
 * @since 2021-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_coupon_goods_relation")
public class CouponGoodsRelation extends Model {

    /**
     * id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 优惠券id
     */
    private String couponId;


    /**
     * 适用维度ID   专区id/类目id/商品id
     */
    private String levelId;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 删除标记 0-未删除 1-已删除
     */
    private Boolean flag;

}
