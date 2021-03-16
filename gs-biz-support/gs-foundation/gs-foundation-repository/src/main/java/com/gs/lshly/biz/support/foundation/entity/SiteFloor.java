package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点楼层
 * </p>
 *
 * @author 陈奇
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_site_floor")
public class SiteFloor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 楼层名
     */
    private String name;

    /**
     * 小图标
     */
    private String icon;

    /**
     * 左侧栏大图(pc)
     */
    private String leftImage;

    /**
     * 顶部广告图(h5)
     */
    private String topImage;

    /**
     * 最大商品显示数量
     */
    private Integer goodsMax;

    /**
     * 显示更多链接
     */
    private String moreUrl;

    /**
     * 图片跳转链接
     */
    private String jumpUrl;

    /**
     * 排序
     */
    private Integer idx;

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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 修改时间
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
