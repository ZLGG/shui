package com.gs.lshly.biz.support.merchant.entity;

import java.math.BigDecimal;
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
 * 店铺商品类目
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_goods_category")
public class ShopGoodsCategory extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 商品类目ID
     */
    private String categoryId;

    /**
     * 商品类目名称
     */
    private String categoryName;

    /**
     * 父类目ID
     */
    private String categoryPid;

    /**
     * 父类目名称
     */
    private String categoryPname;

    /**
     * 商品类目层级
     */
    private Integer categoryLeve;

    /**
     * 适应范围
     */
    private Integer useFiled;

    /**
     * 商品类别平台使用费
     */
    private BigDecimal sharePrice;

    /**
     * 商品类别服务费率
     */
    private BigDecimal fee;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 终端
     */
    private Integer terminal;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 商家ID
     */
    private String merchantId;

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
