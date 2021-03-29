package com.gs.lshly.biz.support.user.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author xxfc
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_shopping_car")
public class UserShoppingCar extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品副标题
     */
    private String goodsTitle;

    /**
     * 是否是积分商品
     */
    private Boolean isPointGood;

    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;

    /**
     * 积分价格
     */
    private BigDecimal goodsPointPrice;

    /**
     * SKU-ID
     */
    private String skuId;

    /**
     * SKU-图片
     */
    private String skuImage;

    /**
     * 规格值
     */
    private String specValue;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 数量
     */
    private Integer quantity;


    /**
     * 会员ID
     */
    private String userId;

    /**
     * 是否选中
     */
    private Integer isSelect;

    /**
     * 终端[10=2b 20=2c]
     */
    private Integer terminal;

    /**
     * 兑换类型（虚拟，实物）
     */
    private Integer exchangeType;

    /**
     * 是否是in会员礼品
     */
    private Boolean isInMemberGift;

    /**
     * in会员积分价格
     */
    private Double inMemberPointPrice;

}