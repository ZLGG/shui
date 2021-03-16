package com.gs.lshly.biz.support.stock.entity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@TableName("gs_stock")
public class Stock extends Model {

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
     * 商品ID
     */
    private String goodsId;

    /**
     * SKU
     */
    private String skuId;

    /**
     * SKU-值
     */
    private String specsKey;

    /**
     * SKU-值
     */
    private String specsValue;

    /**
     * 库存数量
     */
    private Integer quantity;

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