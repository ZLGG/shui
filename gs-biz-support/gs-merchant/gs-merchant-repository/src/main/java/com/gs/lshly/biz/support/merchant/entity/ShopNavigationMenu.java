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
* 店铺导航菜单
* </p>
*
* @author xxfc
* @since 2020-11-04
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_navigation_menu")
public class ShopNavigationMenu extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 店铺自定义分类ID
    */
    private String shopNavigationId;

    /**
    * 菜单图片
    */
    private String menuImage;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
    * 跳转地址
    */
    private String jumpUrl;

    /**
    * 菜单类型[10=自定义分类 20=自定义菜单]
    */
    private Integer menuType;

    /**
    * 终端[10=2b 20=2c]
    */
    private Integer terminal;

    /**
    * 是否PC菜单
    */
    private Integer pcShow;

    /**
    * 排序
    */
    private Integer idx;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

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
