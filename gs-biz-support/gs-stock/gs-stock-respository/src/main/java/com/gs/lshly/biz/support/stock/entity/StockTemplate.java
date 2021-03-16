package com.gs.lshly.biz.support.stock.entity;

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
 * 运费模版主表
 * </p>
 *
 * @author lxus
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_template")
public class StockTemplate extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 运费模板名称
     */
    private String templateName;

    /**
     * 计费类型[10=卖家承担 20=计重 30=计件 40=地区金额]
     */
    private Integer templateType;

    /**
     * 商家id
     */
    private String merchantId;
    /**
     * 店铺id
     */
    private String shopId;
    /**
     * 启用状态[10=启用 20=禁用]
     */
    private Integer state;

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
