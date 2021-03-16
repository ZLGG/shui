package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 运费模板计重计件
 * </p>
 *
 * @author lxus
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_template_unit")
public class StockTemplateUnit extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 运费模板主ID
     */
    private String templateId;

    /**
     * 是否默认[10=是 20=否]
     */
    private Integer isDefault;

    /**
     * 首（计价单位数量）
     */
    private Integer firstQuantity;

    /**
     * 首费
     */
    private BigDecimal firstPrice;

    /**
     * 续（计价单位数量）
     */
    private Integer increaseQuantity;

    /**
     * 续费
     */
    private BigDecimal increasePrice;

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
