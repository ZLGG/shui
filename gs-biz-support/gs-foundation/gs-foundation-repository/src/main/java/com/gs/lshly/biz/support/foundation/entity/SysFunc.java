package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * 平台功能树
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_sys_func")
public class SysFunc implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 图标
     */
    private String icon;

    /**
     * 顺序
     */
    private Integer idx;

    /**
     * 类型(10-根节点;20-模块;30-功能)
     */
    private Integer type;

    /**
     * 父节点code
     */
    private String parent;

    /**
     * 路径
     */
    private String paths;

    /**
     * 所在位置
     */
    private String locations;

    /**
     * 前端路由
     */
    private String frontRouter;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysFunc sysFunc = (SysFunc) o;
        return Objects.equals(id, sysFunc.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
