package com.gs.lshly.biz.support.merchant.entity;

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
* 店铺楼层
* </p>
*
* @author Starry
* @since 2020-11-04
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_floor")
public class ShopFloor extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 楼层名
    */
    private String name;

    /**
    * 小图标
    */
    private String icon;

    /**
    * 商品最多显示数量
    */
    private Integer showNum;

    /**
    * 排序
    */
    private Integer idx;

    /**
    * 终端[10=2b 20=2c]
    */
    private Integer terminal;

    /**
    * 是否PC显示[10=是 20=否]
    */
    private Integer pcShow;

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
