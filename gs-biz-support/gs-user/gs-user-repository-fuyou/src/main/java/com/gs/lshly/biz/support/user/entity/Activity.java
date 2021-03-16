package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author zhaoqiang
 * @since 2020-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("fy_activity")
public class Activity extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动范围：0 全部、1 PC端、2 wap端
     */
    @TableField("`range`")
    private Integer range;

    /**
     * 活动开始时间
     */
    private LocalDateTime startDate;

    /**
     * 活动结束时间
     */
    private LocalDateTime endDate;

    /**
     * 长期有效:0 否，1 是
     */
    private Integer longTermValidity;

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
     * 删除标记：0 未删除、1 已删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean flag;

    /**
     * 活动状态：0 有效、1 失效
     */
    @TableField("`status`")
    private Integer status;

}

