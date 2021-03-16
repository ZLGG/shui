package com.gs.lshly.biz.support.foundation.entity;

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
 * 平台文章栏目
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_data_article_category")
public class DataArticleCategory extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 栏目图标
     */
    private String icon;

    /**
     * 栏目名称
     */
    private String name;

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
     * 父节点
     */
    private String perentId;

    /**
     * 是否第一层级[10=是  20=否]
     */
    private Integer leveone;

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
