package com.gs.lshly.biz.support.foundation.entity;

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
* 站点广告图
* </p>
*
* @author hyy
* @since 2020-11-03
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_site_advert")
public class SiteAdvert extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 图片地址
    */
    private String imageUrl;

    /**
    * 跳转地址
    */
    private String jumpUrl;

    /**
    * 文字
    */
    private String text;

    /**
    * 类型[10=单张广告 20=通栏广告图]
    */
    private Integer advertType;

    /**
    * 是否PC显示[10=是 20=否]
    */
    private Integer pcShow;

    /**
    * 终端[10=2b 20=2c]
    */
    private Integer terminal;

    /**
    * 专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]
    */
    private Integer subject;

    /**
    * 是否商品类目广告
    */
    private Integer isCategory;

    /**
    * 商品类目ID
    */
    private String categoryId;

    /**
     * 排序
     */
    private Integer idx;

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
