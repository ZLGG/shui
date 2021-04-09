package com.gs.lshly.common.struct.bbc.commodity.vo;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.common.CommonShopVO;
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
public abstract class BbcGoodsInfoVO implements Serializable {
	
	@Data
    @ApiModel("BbcGoodsInfoVO.InMemberGoodsVO")
    @Accessors(chain = true)
    public static class InMemberGoodsVO implements Serializable{
		
		@ApiModelProperty("id")
        private String id;
		
        @ApiModelProperty("标题")
        private String name;
        
        @ApiModelProperty("商品内容")
        private PageData<DetailVO> list;
        
        @ApiModelProperty("封面图")
        private String imageUrl;
        
        @ApiModelProperty("积分值")
        private Long telecomsIntegral;
	}

    @Data
    @ApiModel("BbcGoodsInfoVO.ListVO")
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
    @ApiModel("BbcGoodsInfoVO.GoodsListVO")
    public static class GoodsListVO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("规格列表")
        private List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品市场价")
        private BigDecimal oldPrice;

        @ApiModelProperty(value = "spu销量")
        private Integer spuStockNum;

        @ApiModelProperty("单规格的库存")
        private Integer singleSkuStock;

        @ApiModelProperty("商品标签列表")
        private List<GoodsLabelVO> labelVOS = new ArrayList<>();

        @ApiModelProperty(value = "idx顺序",hidden = true)
        private Integer idx;

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

        @ApiModelProperty("销售数量（临时字段）")
        private Integer saleQuantity;

 }
    @Data
    @ApiModel("BbcGoodsInfoVO.GoodsLabelVO")
    public static class GoodsLabelVO implements Serializable{
        @ApiModelProperty("标签id")
        private String id;

        @ApiModelProperty("标签名称")
        private String labelName;

        @ApiModelProperty("标签颜色")
        private String labelColor;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.MerchantGoodsListVO")
    public static class MerchantGoodsListVO implements Serializable{
        @ApiModelProperty("店铺信息")
        private GoodsShopDetailVO shopDetailVO;

        @ApiModelProperty("商品列表")
        private PageData<GoodsListVO> goodsListVOS;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("规格列表")
        private List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty(value = "店铺信息")
        private BbcGoodsInfoVO.GoodsShopDetailVO goodsShopDetailVO;

        @ApiModelProperty(value = "店铺客服信息")
        private CommonShopVO.ShopServiceVO shopServiceVO;

        @ApiModelProperty("多规格商品默认选择规格信息")
        private BbcSkuGoodInfoVO.SkuVO skuVO;

        @ApiModelProperty(value = "spu月销量")
        private Integer spuStockNum;

        @ApiModelProperty(value = "商品市场价")
        private BigDecimal oldPrice;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

        @ApiModelProperty("用户默认收货地址")
        private String userDefaultAddress;

        @ApiModelProperty("商品收藏状态")
        private Integer favoritesState;

        @ApiModelProperty("单规格的库存")
        private Integer singleSkuStock;

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
    @ApiModel("BbcGoodsInfoVO.GoodsShopDetailVO")
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
    @ApiModel("BbcGoodsInfoVO.GoodsSharingVO")
    public static class GoodsSharingVO implements Serializable {

        @ApiModelProperty("微信openId")
        private String openId;

        @ApiModelProperty("微信昵称")
        private String nickName;

        @ApiModelProperty("性别")
        private Integer gender;

        @ApiModelProperty("所在城市")
        private String city;

        @ApiModelProperty("所在省")
        private String province;

        @ApiModelProperty("所在国家")
        private String country;

        @ApiModelProperty("微信头像地址")
        private String avatarUrl;

        @ApiModelProperty("微信unionId")
        private String unionId;

        @ApiModelProperty("state 0=失败 1=成功")
        private Integer state;

    }

    @Data
    @ApiModel("BbcGoodsInfoVO.InVIPSpecialAreaVO")
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


    //----------------内部服务VO-----------------------------

    @Data
    @ApiModel("BbcGoodsInfoVO.HomeAndShopInnerServiceVO")
    public static class HomeAndShopInnerServiceVO implements Serializable{
        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("规格列表")
        private List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("划线价")
        private BigDecimal oldPrice;

        @ApiModelProperty("单规格的库存")
        private Integer singleSkuStock;

        @ApiModelProperty("商品标签列表")
        private List<GoodsLabelVO> labelVOS = new ArrayList<>();

        @ApiModelProperty(value = "排序",hidden = true)
        private Integer idx;

    }


    @Data
    @ApiModel("BbcGoodsInfoVO.InnerServiceVO")
    public static class InnerServiceVO implements Serializable {

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("POS商品ID")
        private String posSpuId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品货号")
        private String goodsNo;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("sku规格值")
        private String skuSpecValue;

        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("商品状态")
        private Integer goodsState;

        @ApiModelProperty("条形码")
        private String barcode;

        @ApiModelProperty("兑换类型（虚拟，实物）")
        private Integer exchangeType;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private BigDecimal inMemberPointPrice;

        @ApiModelProperty("划线价")
        private BigDecimal oldPrice;
        
        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;
        

    }



    @Data
    @ApiModel("BbcGoodsInfoVO.InnerGoodsKitSkuVO")
    public static class InnerGoodsKitSkuVO implements Serializable {


    }

}
