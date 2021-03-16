package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
* <p>
* 售后凭证表
* </p>
*
* @author oy
* @since 2020-12-06
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_rights_img")
public class TradeRightsImg extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 售后凭证ID
    */
    private String id;

    /**
    * 售后表ID
    */
    private String rightsId;

    /**
    * 订单ID
    */
    private String tradeId;

    /**
    * 凭证图片
    */
    private String rightsImg;

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
