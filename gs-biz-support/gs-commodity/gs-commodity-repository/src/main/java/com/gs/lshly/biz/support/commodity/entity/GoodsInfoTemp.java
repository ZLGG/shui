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
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_goods_info_temp")
public class GoodsInfoTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    private String id;

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
     * 商品状态（10，下架；20，上架）
     */
    private Integer goodsState;

    /**
     * 商品货号
     */
    private String goodsNo;

    /**
     * 商品售价
     */
    private BigDecimal salePrice;

    /**
     * 商品原价(商品市场价）
     */
    private BigDecimal oldPrice;

    /**
     * 商品阶梯价
     */
    private String stepPrice;

    /**
     * 商品成本价
     */
    private BigDecimal costPrice;

    /**
     * 商家条形码
     */
    private String goodsBarcode;

    /**
     * 电脑端商品描述
     */
    private String goodsPcDesc;

    /**
     * 移动端商品描述
     */
    private String goodsH5Desc;

    /**
     * 商品有效期
     */
    private Integer goodsValidDays;

    /**
     * 商品图片组
     */
    private String goodsImage;

    /**
     * 商品计价单位
     */
    private String goodsPriceUnit;

    /**
     * 是否显示原价
     */
    private Integer isShowOldPrice;

    /**
     * 是否是扶贫商品(10=标准商品 20=扶贫商品）
     */
    private Integer isSuportPoorGoods;

    /**
     * 单品或者多规格商品（10 = 单品，20=多规格）
     */
    private Integer isSingle;

    /**
     * 使用平台
     */
    private Integer usePlatform;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 商品重量
     */
    private BigDecimal goodsWeight;

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
     * in会员优惠券类型（20,30,50,99,200）
     */
    private Integer inCouponType;

    /**
     * 出售类型（0普通，1活动）
     */
    private Integer saleType;

    /**
     * 信天游产品号
     */
    private Integer thirdProductId;

    /**
     * 销售数量（临时排序用）
     */
    private Integer saleQuantity;

    /**
     * 兑换类型（20实物，10虚拟）
     */
    private Integer exchangeType;

    /**
     * 商品来源  10  电信自营  20 第三方提供
     */
    private Integer sourceType;

    /**
     * 点击量（临时）
     */
    private Integer clickVolume;

    /**
     * 视频地址
     */
    private String videoUrl;

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
