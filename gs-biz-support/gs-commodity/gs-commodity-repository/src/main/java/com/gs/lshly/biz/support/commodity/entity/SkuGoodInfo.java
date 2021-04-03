package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author Starry
 * @since 2020-10-08
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_sku_good_info")
public class SkuGoodInfo extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * sku商品id
     */
    private String id;

    /**
     * pos店铺spuID
     */
    private String posSpuId;

    /**
     * 商品id
     */
    private String goodId;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 类目id
     */
    private String categoryId;

    /**
     * 规格id组
     */
    private String specsKey;

    /**
     * 规格值组
     */
    private String specsValue;

    /**
     * sku商品货号
     */
    private String skuGoodsNo;

    /**
     * 条形码
     */
    private String barcode;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 商品售价
     */
    private BigDecimal salePrice;

    /**
     * 商品原价
     */
    private BigDecimal oldPrice;

    /**
     *商品成本价
     */
    private BigDecimal costPrice;

    /**
     *商品阶梯价
     */
    private String stepPrice;

    /**
     * 商品状态
     */
    private Integer state;

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
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;

    @ApiModelProperty("积分价格")
    private BigDecimal pointPrice;

    @ApiModelProperty("办理备注")
    private String remarks;

    @ApiModelProperty("是否是积分商品")
    private Boolean isPointGood;

    @ApiModelProperty("是否是in会员礼品")
    private Boolean isInMemberGift;

    @ApiModelProperty("in会员积分价格")
    private BigDecimal inMemberPointPrice;

}
