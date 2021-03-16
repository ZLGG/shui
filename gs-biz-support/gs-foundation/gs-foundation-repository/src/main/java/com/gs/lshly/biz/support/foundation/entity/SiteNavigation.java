package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点导航
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_site_navigation")
public class SiteNavigation extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 导航名称
     */
    private String name;

    /**
     * 链接地址
     */
    private String url;

    /**
     * 热点图片(顶部导航专属)
     */
    private String hotImageUrl;

    /**
     * 导航类型[10=顶部链接 20=菜单导航]
     */
    private Integer type;

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
     * 专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]
     */
    private Integer subject;

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
