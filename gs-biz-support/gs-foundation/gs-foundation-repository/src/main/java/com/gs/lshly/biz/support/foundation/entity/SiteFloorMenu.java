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
 * 楼层菜单（PC）
 * </p>
 *
 * @author 陈奇
 * @since 2020-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_site_floor_menu")
public class SiteFloorMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 楼层id
     */
    private String floorId;

    /**
     * 菜单名
     */
    private String menuName;

    /**
     * 菜单类型[10=楼层顶部 20=左侧链接]
     */
    private Integer menuType;

    /**
     * 链接地址
     */
    private String jumpUrl;

    /**
     * 开窗类型[10=当前 20=新窗]
     */
    private Integer openType;

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
