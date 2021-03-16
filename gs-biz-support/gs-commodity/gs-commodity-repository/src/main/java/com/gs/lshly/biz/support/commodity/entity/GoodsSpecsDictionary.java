package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_specs_dictionary")
public class GoodsSpecsDictionary extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 公共商品规格id
     */
    private String id;


    /**
     * 公共商品规格名称
     */
    private String name;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer idx;

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
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean flag;


}
