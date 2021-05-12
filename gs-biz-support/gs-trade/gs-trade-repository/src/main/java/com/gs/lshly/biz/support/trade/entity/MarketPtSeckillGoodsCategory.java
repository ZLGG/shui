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
 * 平台活动商品的类目
 * </p>
 *
 * @author yingjun
 * @since 2021-05-07
 */
@SuppressWarnings("rawtypes")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_market_pt_seckill_goods_category")
public class MarketPtSeckillGoodsCategory extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 商品类目id
     */
    private String categoryId;

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
