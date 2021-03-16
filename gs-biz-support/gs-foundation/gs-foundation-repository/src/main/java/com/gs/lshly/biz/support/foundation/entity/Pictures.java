package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Starry
 * @since 2020-10-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_pictures")
public class Pictures extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 图库id
     */
    private String id;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 商家id(默认为-1:平台）
     */
    private String merchantId;

    /**
     * 分组id
     */
    private String groupId;

    /**
     * 存储引擎
     */
    private String storageEngine;

    /**
     * 文件大小
     */
    private String size;

    /**
     * 图片高度
     */
    private Integer imgHeight;

    /**
     * 图片宽度
     */
    private Integer imgWeight;

    /**
     * 文件格式
     */
    private String imgType;

    /**
     * 图片名字
     */
    private String imageName;

    /**
     * 图片路径
     */
    private String imageUrl;

    /**
     * 图片来源（业务位置)
     */
    private String source;

    /**
     * 是否隐藏（1隐藏,0不隐藏）
     */
    private Integer hidden;

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
     * 删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
