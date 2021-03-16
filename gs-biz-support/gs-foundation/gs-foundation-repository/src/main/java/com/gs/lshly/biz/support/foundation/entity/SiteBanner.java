package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 站点轮播图
 * </p>
 *
 * @author 陈奇
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_site_banner")
public class SiteBanner implements Serializable {

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
     * 文字
     */
    private String text;

    /**
     * 跳转地址
     */
    private String jumpUrl;

    /**
     * 播放速度
     */
    private Float speed;

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
     * 是否是分类[1=是 0=否]
     */
    private Integer isClassify;

    /**
     * 产品类目id
     */
    private String productCategoryId;

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
