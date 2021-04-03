package com.gs.lshly.common.struct.bbb.pc.commodity.vo;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.BbbShopVO;
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
public abstract class PCBbbGoodsInfoVO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsInfoVO.ListVO")
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
    @ApiModel("PCBbbGoodsInfoVO.GoodsListVO")
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

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品阶梯价")
        private List<GoodsStepPriceVO> stepPrice;

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

        @ApiModelProperty(value = "idx",hidden = true)
        private Integer idx;

        @ApiModelProperty("视频地址")
        private String videoUrl;

    }

    @Data
    @ApiModel("PCBbbGoodsInfoVO.GoodsRecommendVO")
    public static class GoodsRecommendVO implements Serializable{
        //TODO 推荐图标

        @ApiModelProperty("推荐商品列表")
        private List<GoodsListVO> goodsListVOS;
    }


    @Data
    @ApiModel("PCBbbGoodsInfoVO.GoodsDetailListVO")
    public static class GoodsDetailListVO extends GoodsListVO{

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("商品收藏状态")
        private Integer favoriteState = TrueFalseEnum.否.getCode();

        @ApiModelProperty("单规格商品库存")
        private Integer singleGoodsStock;

        @ApiModelProperty("sku信息列表")
        private List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuDetailListVOS = new ArrayList<>();

        @ApiModelProperty(value = "idx",hidden = true)
        private Integer idx;

    }

    @Data
    @ApiModel("PCBbbGoodsInfoVO.WholesalePriceVO")
    public static class WholesalePriceVO implements Serializable{

        @ApiModelProperty("批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("阶梯价")
        private List<GoodsStepPriceVO> stepPriceVOS = new ArrayList<>();

    }


    @Data
    @ApiModel("PCBbbGoodsInfoVO.GoodsDetailVO")
    public static class GoodsDetailVO extends GoodsListVO{

        @ApiModelProperty("商品收藏状态")
        private Integer favoriteState = TrueFalseEnum.否.getCode();

        @ApiModelProperty("店铺信息")
        private BbbShopVO.ShopScoreDetailVO shopInfo;

        //TODO 商品评分
        @ApiModelProperty("二维码图片")
        private String code;

        @ApiModelProperty("商品详情")
        private String goodsPcDesc;

        @ApiModelProperty("单规格商品库存")
        private Integer singleGoodsStock;

        @ApiModelProperty("默认的sku规格数据")
        private PCBbbSkuGoodInfoVO.SkuDetailListVO defaultSkuInfo;

        @ApiModelProperty("相册图片")
        private List<String> imagesList;

        @ApiModelProperty("sku规格列表")
        private List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuDetailListVOS = new ArrayList<>();

        @ApiModelProperty(value = "销售数量")
        private Integer saleQuantity;

    }


    @Data
    @ApiModel("PCBbbGoodsInfoVO.GoodsStepPriceVO")
    public static class GoodsStepPriceVO implements Serializable{
        @ApiModelProperty("起始区间")
        private Integer startNum;

        @ApiModelProperty("结束区间")
        private Integer endNum;

        @ApiModelProperty("区间批发价")
        private BigDecimal stepPrice;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoVO.GetGoodsStepPriceVO")
    public static class GetGoodsStepPriceVO implements Serializable{

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("阶梯阶")
        private List<GoodsStepPriceVO> goodsStepPriceList;
    }

    //----------------内部服务VO-----------------------------

    @Data
    @ApiModel("PCBbbGoodsInfoVO.ShopInnerServiceVO")
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
        private List<GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("单规格商品库存数量")
        private Integer singleGoodsStockNum;

        @ApiModelProperty("sku列表")
        private List<PCBbbSkuGoodInfoVO.SkuDetailListVO> skuDetailListVOS = new ArrayList<>();

        @ApiModelProperty("商品收藏状态")
        private Integer favoriteState = TrueFalseEnum.否.getCode();

        @ApiModelProperty(value = "排序暂位",hidden = true)
        private Integer idx;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoVO.HomeInnerServiceVO")
    public static class HomeInnerServiceVO implements Serializable{

        @ApiModelProperty("商品id")
        private String id;

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
        private List<PCBbbGoodsInfoVO.GoodsStepPriceVO> stepPrice;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoVO.GoodsPriceVO")
    public static class GoodsPriceVO implements Serializable {

        @ApiModelProperty("商品阶梯价")
        private List<PCBbbGoodsInfoVO.GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoVO.InnerServiceVO")
    public static class InnerServiceVO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("POS商品ID")
        private String posSpuId;

        @ApiModelProperty("商品条码")
        private String barcode;

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

        @ApiModelProperty("商品阶梯价")
        private List<GoodsStepPriceVO> stepPrice;

        @ApiModelProperty("商品批发价")
        private BigDecimal wholesalePrice;

        @ApiModelProperty("sku规格id")
        private String skuSpecKey;

        @ApiModelProperty("sku规格值")
        private String skuSpecValue;

        @ApiModelProperty("SKU商品货号")
        private String skuGoodsNo;

        @ApiModelProperty("商品状态")
        private Integer goodsState;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商品重量")
        private BigDecimal goodsWeight;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty(value = "扩展业务商品数量")
        private Integer sQuantity;

        @ApiModelProperty(value = "扩展业务ID")
        private String  sid;

    }

}
