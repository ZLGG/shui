package com.gs.lshly.biz.support.commodity.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyang
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_sku_good_info_temp")
public class SkuGoodInfoTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * sku商品id
     */
    private String id;

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
     * 规格拓展id组
     */
    private String specsKey;

    /**
     * 规格拓展值组
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
     * 阶梯价格
     */
    private String stepPrice;

    /**
     * 商品原价
     */
    private BigDecimal oldPrice;

    /**
     * 商品成本价
     */
    private BigDecimal costPrice;

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
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 删除标记
     */
    private Boolean flag;

    private String posSpuId;

    /**
     * 积分价格
     */
    private BigDecimal pointPrice;

    private BigDecimal oldPointPrice;

    /**
     * 办理备注
     */
    private String remarks;

    /**
     * 是否是积分商品
     */
    private Boolean isPointGood;

    /**
     * 是否是in会员礼品
     */
    private Boolean isInMemberGift;

    /**
     * in会员积分价格
     */
    private BigDecimal inMemberPointPrice;

    /**
     * 结算积分价格
     */
    private BigDecimal settlementPointPrice;

    /**
     * 结算现金价格
     */
    private BigDecimal settlementPrice;

    /**
     * 电信商品分类 10：普通商品 20：积分商品 30：IN会员商品；40：定向积分商品
     */
    private Integer ctccMold;


}
