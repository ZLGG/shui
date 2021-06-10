package com.gs.lshly.biz.support.commodity.entity;

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
 * 
 * </p>
 *
 * @author yingjun
 * @since 2021-06-09
 */
@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_goods_ctcc_api")
public class GoodsCtccApi extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private String id;

    /**
     * 商家id
     */
    private String goodId;

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
     * 删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;

    private Integer ctccApi;

    /**
     * 优惠编码
     */
    private String couponCode;

    /**
     * 优惠名称
     */
    private String couponName;

    /**
     * 对应优惠编码
     */
    private String replyCouponCode;

    private String replyCouponName;

    private String attrId;

    private String attrValue;

    private String attrIdPoint;

    private String attrValuePoint;


}
