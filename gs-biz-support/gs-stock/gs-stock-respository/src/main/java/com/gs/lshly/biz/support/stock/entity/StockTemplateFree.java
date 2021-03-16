package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.math.BigDecimal;
import java.time.LocalDateTime;

    /**
    * <p>
    * 运费模板条件包邮
    * </p>
    *
    * @author lxus
    * @since 2020-10-26
    */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_template_free")
public class StockTemplateFree extends Model {

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
        * 是否默认[0=否 1=是]
        */
        private Integer isDefault;

        /**
        * 首（计价单位数量）
        */
        private Integer quantity;

        /**
        * 金额
        */
        private BigDecimal price;

        /**
        * 10=计价单位数量 20=金额 30=组合(计价单位数量+金额)
        */
        private Integer mathType;

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
