package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* <p>
* 运费模板地区金额
* </p>
*
* @author lxus
* @since 2020-11-17
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_template_quota_price")
public class StockTemplateQuotaPrice extends Model {

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
    * 金额区域id
    */
    private String quotaId;

    /**
    * 范围小
    */
    private BigDecimal minValue;

    /**
    * 范围大
    */
    private BigDecimal maxValue;

    /**
    * 金额
    */
    private BigDecimal price;

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
