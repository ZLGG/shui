package com.gs.lshly.common.struct.bbb.h5.commodity.vo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbSkuGoodInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsSpecInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-10-23
*/
public abstract class BbbH5GoodsInfoVO implements Serializable {

    @Data
    @ApiModel("BbbH5GoodsInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商品id")
        private String id;


        @ApiModelProperty("商家id")
        private String merchantId;


        @ApiModelProperty("店铺id")
        private String shopId;


        @ApiModelProperty("品牌id")
        private String brandId;


        @ApiModelProperty("类目id")
        private String categoryId;


        @ApiModelProperty("商品拓展规格id")
        private String specInfoId;


        @ApiModelProperty("商品拓展属性id")
        private String attributeInfoId;


        @ApiModelProperty("商品拓展参数id")
        private String extendParamsId;


        @ApiModelProperty("商品名称")
        private String goodsName;


        @ApiModelProperty("商品标题")
        private String goodsTitle;


        @ApiModelProperty("商品状态")
        private Integer goodsState;


        @ApiModelProperty("商品货号")
        private String goodsNo;


        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;


        @ApiModelProperty("商品原价")
        private BigDecimal oldPrice;


        @ApiModelProperty("商品阶梯价")
        private String stepPrice;


        @ApiModelProperty("商品成本价")
        private BigDecimal costPrice;


        @ApiModelProperty("商家条形码")
        private String goodsBarcode;


        @ApiModelProperty("电脑端商品描述")
        private String goodsPcDesc;


        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;


        @ApiModelProperty("商品有效期")
        private Integer goodsValidDays;


        @ApiModelProperty("商品图片组")
        private String goodsImage;


        @ApiModelProperty("商品计价单位")
        private String goodsPriceUnit;


        @ApiModelProperty("是否显示原价")
        private Integer isShowOldPrice;


        @ApiModelProperty("是否是扶贫商品")
        private Integer isSuportPoorGoods;


        @ApiModelProperty("单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;


        @ApiModelProperty("使用平台")
        private Integer usePlatform;


        @ApiModelProperty("发布时间")
        private LocalDateTime publishTime;


        @ApiModelProperty("商品重量")
        private BigDecimal goodsWeight;


        @ApiModelProperty("操作人")
        private String operator;

    }


    @Data
    @ApiModel("BbbH5GoodsInfoVO.SimpleCommentGoodsVO")
    public static class SimpleCommentGoodsVO implements Serializable{
        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("单规格的skuId(或者为多规格的默认skuId)")
        private String skuId;

        @ApiModelProperty("单规格=10 多规格=20")
        private Integer isSingle;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("积分价格")
        private Double pointPrice;

        @ApiModelProperty("办理备注")
        private String remarks;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private Double inMemberPointPrice;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;

        @ApiModelProperty("兑换类型（虚拟，实物）")
        private Integer exchangeType;

        @ApiModelProperty("视频地址")
        private String videoUrl;
    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.ComplexCommentGoodsVO")
    public static class ComplexCommentGoodsVO extends SimpleCommentGoodsVO{
        @ApiModelProperty("商品阶梯价")
        private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("单规格商品库存")
        private Integer singleGoodsStock;

        @ApiModelProperty("sku规格列表")
        private List<BbbH5GoodsSpecInfoVO.SpecListVO> specListVOS;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.GoodsStepPriceVO")
    public static class GoodsStepPriceVO implements Serializable{
        @ApiModelProperty("起始区间")
        private Integer startNum;

        @ApiModelProperty("结束区间")
        private Integer endNum;

        @ApiModelProperty("区间批发价")
        private BigDecimal stepPrice;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoVO.WholesalePriceVO")
    public static class WholesalePriceVO implements Serializable{

        @ApiModelProperty("批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("阶梯价")
        private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPriceVOS = new ArrayList<>();

    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.GoodsListVO")
    public static class GoodsListVO extends SimpleCommentGoodsVO {

        @ApiModelProperty(value = "spu月销量")
        private Integer spuStockNum;

        @ApiModelProperty("单规格商品的库存")
        private Integer singleSkuStock;

        @ApiModelProperty("多规格默认的sku信息")
        private BbbH5SkuGoodInfoVO.SkuVO skuVO;

        @ApiModelProperty(value = "idx",hidden = true)
        private Integer idx;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.GoodsPriceVO")
    public static class GoodsPriceVO implements Serializable {

        @ApiModelProperty("商品阶梯价")
        private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;
    }



    @Data
    @ApiModel("BbbH5GoodsInfoVO.MerchantGoodsListVO")
    public static class MerchantGoodsListVO implements Serializable{
        @ApiModelProperty("店铺信息")
        private GoodsShopDetailVO shopDetailVO;

        @ApiModelProperty("商品列表")
        private PageData<GoodsListVO> goodsListVOS;
    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("单规格商品skuId或多规格默认skuId")
        private String skuId;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品规格类型 多规格=20 单品=10")
        private Integer IsSingle;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品市场价")
        private BigDecimal oldPrice;

        @ApiModelProperty(value = "店铺信息")
        private BbbH5GoodsInfoVO.GoodsShopDetailVO goodsShopDetailVO;

        @ApiModelProperty("多规格商品默认选择规格信息")
        private BbbH5SkuGoodInfoVO.SkuVO skuVO;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

        //TODO 配送区域


        @ApiModelProperty("商品收藏状态")
        private Integer favoritesState;

        @ApiModelProperty("单规格的库存")
        private Integer singleSkuStock;

        @ApiModelProperty("商品阶梯价")
        private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("用户默认收货地址")
        private String userDefaultAddress;

        @ApiModelProperty("积分价格")
        private Double pointPrice;

        @ApiModelProperty("办理备注")
        private String remarks;

        @ApiModelProperty("是否是积分商品")
        private BigDecimal isPointGood;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private Double inMemberPointPrice;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;

        @ApiModelProperty(value = "销售数量")
        private Integer saleQuantity;

        @ApiModelProperty("兑换类型（虚拟，实物）")
        private Integer exchangeType;

        @ApiModelProperty("视频地址")
        private String videoUrl;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.GoodsShopDetailVO")
    public static class GoodsShopDetailVO implements Serializable{
        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("店铺logo")
        private String shopLogo;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDescribe;

        @ApiModelProperty("店铺评分")
        private BigDecimal shopScore;
    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.InVIPSpecialAreaVO")
    public static class InVIPSpecialAreaVO implements Serializable{
        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品原价")
        private BigDecimal oldPrice;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

        @ApiModelProperty("可抵扣价格")
        private Integer inCouponType;

        @ApiModelProperty("in会员券后价格")
        private BigDecimal inDiscountedPrice;

        @ApiModelProperty("品牌id")
        private String brandId;

        @ApiModelProperty("品牌名称")
        private String brandName;

        @ApiModelProperty("商品拓展规格id")
        private String specInfoId;

        @ApiModelProperty("商品拓展规格名字")
        private String specName;

        @ApiModelProperty("商品拓展规格值名称")
        private String specValue;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.IntegralGoodsInfo")
    public static class IntegralGoodsInfo implements Serializable{
        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

        @ApiModelProperty("品牌id")
        @JsonIgnore
        private String brandId;

        @ApiModelProperty("品牌名称")
        private String brandName;

        @ApiModelProperty("店铺id")
        @JsonIgnore
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("商品销量")
        private Integer saleQuantity;

        @ApiModelProperty("积分价格")
        private Double pointPrice;

    }



    //----------------内部服务VO-----------------------------

    @Data
    @ApiModel("BbbH5GoodsInfoVO.ShopInnerServiceVO")
    public static class ShopInnerServiceVO implements Serializable{
        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("sku商品Id")
        private String skuId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("商品阶梯价")
        private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("单规格商品库存数量")
        private Integer singleGoodsStockNum;

        @ApiModelProperty("规格列表")
        private List<BbbH5GoodsSpecInfoVO.SpecListVO> specListVOS;

        //TODO 商品收藏状态
    }

    @Data
    @ApiModel("BbbH5GoodsInfoVO.HomeInnerServiceVO")
    public static class HomeInnerServiceVO implements Serializable{

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("单规格商品skuId或是多规格的默认skuId")
        private String skuId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("单规格商品库存数量")
        private Integer singleGoodsStockNum;

        @ApiModelProperty("默认多规格的sku信息")
        private BbbH5SkuGoodInfoVO.SkuVO skuVO;
    }


    @Data
    @ApiModel("BbbH5GoodsInfoVO.InnerServiceVO")
    public static class InnerServiceVO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商品货号")
        private String goodsNo;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品阶梯价")
        private List<BbbH5GoodsInfoVO.GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("sku规格值")
        private String skuSpecValue;

        @ApiModelProperty("sku规格id")
        private String skuSpecKey;

        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("商品状态")
        private Integer goodsState;

        @ApiModelProperty(value = "扩展业务商品数量")
        private Integer sQuantity;

        @ApiModelProperty(value = "扩展业务ID")
        private String  sid;

    }

}
