package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Starry
 * @since 2020-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_category_brand")
public class CategoryBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 类别id
     */
    private String categoryId;

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
