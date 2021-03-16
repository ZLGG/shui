package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 平台文章
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_data_article")
public class DataArticle extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * logo
     */
    private String logo;

    /**
     * 内容
     */
    private String content;

    /**
     * 阅读量
     */
    private Integer readCount;

    /**
     * 是否PC显示[10=PC 20=H5  30=全部]
     */
    private Integer pcShow;

    /**
     * 是否默认显示
     */
    private Integer isDefault;

    /**
     * 终端[10=2b 20=2c]
     */
    private Integer terminal;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 发布时间
     */
    private LocalDateTime sendTime;


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
