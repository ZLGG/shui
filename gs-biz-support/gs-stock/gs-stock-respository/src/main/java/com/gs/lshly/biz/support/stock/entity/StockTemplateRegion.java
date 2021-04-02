package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
* <p>
* 运费模板支持地区表
* </p>
*
* @author lxus
* @since 2020-10-26
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_template_region")
public class StockTemplateRegion extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    private String id;

    /**
    * 运费模板子表ID
    */
    private String childTemplateId;

    /**
    * 运费模板主表ID
    */
    private String templateId;

    /**
    * 运费模板子表类型[10=包邮条件 20=计重计件 30=地区金额]
    */
    private Integer childTemplateType;

    /**
     * 省id
     */
    private String provinceId;

    /**
    * 省
    */
    private String province;

    /**
     * 市id
     */
    private String cityId;

    /**
    * 市
    */
    private String city;

    /**
     * 区id
     */
    private String countyId;

    /**
    * 区
    */
    private String county;

    /**
    * 最小地区层级[10=省 20=市 30=区]
    */
    private Integer regionLeve;

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
