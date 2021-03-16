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
* 店铺广告图
* </p>
*
* @author Starry
* @since 2020-10-30
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_advert")
public class ShopAdvert extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 跳转链接
    */
    private String jumpUrl;

    /**
    * 图片地址
    */
    private String imageUrl;

    /**
     * 描述
     */
    private String text;

    /**
    * 广告类型[10=通栏广告 20=单张广告]
    */
    private Integer advertType;

    /**
    * 终端[10=2b 20=2c]
    */
    private Integer terminal;

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
