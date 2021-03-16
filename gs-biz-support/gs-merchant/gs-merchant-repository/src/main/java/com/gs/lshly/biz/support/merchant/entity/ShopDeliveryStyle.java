package com.gs.lshly.biz.support.merchant.entity;

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
* 配送方式
* </p>
*
* @author xxfc
* @since 2020-11-07
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_delivery_style")
public class ShopDeliveryStyle extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    private String id;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 配送类型(多选)[10=快递 20=自提 30=门店配送]
    */
    private String deliveryTypes;

    /**
    * 收费方式[10=自定义费用  20=按重量 30=按件数]
    */
    private Integer shopBillType;

    /**
    * 自定义配送费用
    */
    private BigDecimal shopCustomizePrice;

    /**
    * 配送范围（公里）
    */
    private Integer shopRanges;

    /**
     * 首件
     */
    private Integer shopFirstQuantity;

    /**
    * 首件费
    */
    private BigDecimal shopFirstQuantityPrice;

    /**
     * 续件
     */
    private Integer shopIncreaseQuantity;

    /**
    * 续件费
    */
    private BigDecimal shopIncreaseQuantityPrice;

    /**
     * 首重
     */
    private Integer shopFirstWeight;

    /**
     * 首重费
     */
    private BigDecimal shopFirstWeightPrice;

    /**
     * 续重
     */
    private Integer shopIncreaseWeight;

    /**
     * 续重费
     */
    private BigDecimal shopIncreaseWeightPrice;


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
