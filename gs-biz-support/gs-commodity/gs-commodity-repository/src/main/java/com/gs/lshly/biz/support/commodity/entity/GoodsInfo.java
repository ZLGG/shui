package com.gs.lshly.biz.support.commodity.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Starry
 * @since 2020-10-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_info")
public class GoodsInfo extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private String id;

    /**
     * pos店铺spuID
     */
    private String posSpuId;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 店铺id
     */
    private String shopId;


    /**
     * 品牌id
     */
    private String brandId;

    /**
     * 类目id
     */
    private String categoryId;

    /**
     * 商品拓展规格id
     */
    private String specInfoId;

    /**
     * 商品拓展属性id
     */
    private String attributeInfoId;

    /**
     * 商品拓展参数id
     */
    private String extendParamsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品标题
     */
    private String goodsTitle;

    /**
     * 商品状态
     */
    private Integer goodsState;

    /**
     * 商品编号
     */
    private String goodsNo;
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
     * 商品重量
     */
    private BigDecimal goodsWeight;

    /**
     * 移动端商品描述
     */
    private String goodsH5Desc;

    /**
     * 电脑端商品描述
     */
    private String goodsPcDesc;

    /**
     * 商家条形码
     */
    private String goodsBarcode;

    /**
     * 商品有效期
     */
    private Integer goodsValidDays;

    /**
     * 商品图片组
     */
    private String goodsImage;

    /**
     * 是否是扶贫商品
     */
    private Integer isSuportPoorGoods;

    /**
     * 使用平台
     */
    private Integer usePlatform;

    /**
     * 是否显示原价
     */
    private Integer isShowOldPrice;

    /**
     * 商品计价单位
     */
    private String goodsPriceUnit;
    /**
     * 单品或者多规格商品（10 = 单品，20=多规格）
     */
    private Integer isSingle;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

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


    /**
     * 积分价格
     */
    private BigDecimal pointPrice;
    
    /**
     * 原积分价格
     */
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
     * 出售类型（0普通，1活动）
     */
    private Integer saleType;

    /**
     * 信天游产品号
     */
    private Integer thirdProductId;

    /**
     * 销售数量（临时字段）
     */
    private Integer saleQuantity;

    /**
     * 兑换类型（虚拟，实物）
     */
    private Integer exchangeType;

    /**
     * 点击量（临时）
     */
    private Integer clickVolume;

    /**
     * 视频地址
     */
    private String videoUrl;

    /**
     * in会员优惠券类型（20,30,50,99,200）
     */
    private Integer inCouponType;
    
    
    /**
     * 关联电信API入口 10:b2i;  20:统一支付；30:bss3.0；40:itv；50:信天游
     */
    private Integer ctccApi;

    /**
     * '电信商品分类 10：普通商品 20：积分商品 30：IN会员商品；40：定向积分商品'
     */
    private Integer ctccMold;
}
