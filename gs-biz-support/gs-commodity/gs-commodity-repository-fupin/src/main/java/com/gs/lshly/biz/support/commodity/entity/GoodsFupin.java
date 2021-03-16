package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.time.LocalDateTime;

/**
* <p>
* 
* </p>
*
* @author Starry
* @since 2020-12-08
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_fupin")
public class GoodsFupin extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 扶贫商品id
    */
    private String id;

    /**
    * 商品id
    */
    private String goodsId;

    /**
    * 店铺id
    */
    private String shopId;

    /**
    * 商家id
    */
    private String merchantId;

    /**
    * 操作人
    */
    private String operator;

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


}
