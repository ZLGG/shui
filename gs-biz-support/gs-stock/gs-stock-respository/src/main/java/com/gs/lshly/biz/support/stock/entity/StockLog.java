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
* 库存管理
* </p>
*
* @author xxfc
* @since 2020-11-02
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_log")
public class StockLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    private String id;

    /**
    * 库存总表ID
    */
    private String stockId;

    /**
    * 商品ID
    */
    private String goodsId;

    /**
    * SKU
    */
    private String skuId;

    /**
    * 数据来源[10=POS 20=商家运维 30=order 40=采购单]
    */
    private Integer dataFromType;

    /**
    * 动作类型[10=同步 20=修改]
    */
    private Integer dataActionType;

    /**
    * 改变的数量
    */
    private Integer quantityChange;

    /**
    * 当前库存总量
    */
    private Integer quantityAll;

    /**
    * 备注
    */
    private String remark;

    /**
     * 仓库变动方向[10=增加 20=减少]
     */
    private Integer location;


    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 店铺ID
    */
    private String shopId;

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
