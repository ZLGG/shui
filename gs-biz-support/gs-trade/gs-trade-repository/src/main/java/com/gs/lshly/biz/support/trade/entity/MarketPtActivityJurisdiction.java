package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
* <p>
* 
* </p>
*
* @author zdf
* @since 2021-03-09
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_market_pt_activity_jurisdiction")
public class MarketPtActivityJurisdiction extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 配置活动表id
    */
    private String id;

    /**
    * pc端满减
    */
    private Integer pcCut;

    /**
    * pc端满赠
    */
    private Integer pcGift;

    /**
    * pc端团购
    */
    private Integer pcGroupbuy;

    /**
    * pc端满折
    */
    private Integer pcDiscount;

    /**
    * h5端满减
    */
    private Integer h5Cut;

    /**
    * h5端满赠
    */
    private Integer h5Gift;

    /**
    * h5端团购
    */
    private Integer h5Groupbuy;

    /**
    * h5端满折
    */
    private Integer h5Discount;

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
