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
 * 
 * </p>
 *
 * @author Starry
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_pictures_setting")
public class PicturesSetting extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 默认图片路径
     */
    private String imageUrl;

    /**
     * 默认图片名称
     */
    private String imageName;

    /**
     * 存储引擎
     */
    private String storageEnginee;

    /**
     * 图片高度
     */
    private Integer imgHeight;

    /**
     * 图片宽度
     */
    private Integer imgWeight;

    /**
     * 图片格式
     */
    private String imageType;

    /**
     * 图片尺寸类型
     */
    private Integer imageSizeType;

    /**
     * 操作人
     */
    private String operator;

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
