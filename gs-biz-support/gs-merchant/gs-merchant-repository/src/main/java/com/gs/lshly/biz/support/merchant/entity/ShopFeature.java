package com.gs.lshly.biz.support.merchant.entity;

import java.math.BigDecimal;
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
* 店铺信息扩展
* </p>
*
* @author xxfc
* @since 2020-12-04
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_feature")
public class ShopFeature extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    private String shopId;

    private String  merchantId;

    /**
    * 评分
    */
    private BigDecimal score;

    /**
    * 销量
    */
    private Integer sales;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;


    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
