package com.gs.lshly.common.struct.bbc.commodity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gs.lshly.common.enums.GoodsCouponStatusEnum;
import com.gs.lshly.common.enums.GoodsCouponTypeEnum;
import com.gs.lshly.common.enums.MarketCheckTypeEnum;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.bbc.foundation.vo.BbcSiteAdvertVO;
import com.gs.lshly.common.struct.common.CommonShopVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Starry
 * @since 2020-10-23
 */
@SuppressWarnings("serial")
public abstract class BbcGoodsInfoVO implements Serializable {

    /**
     * 简单列表数据
     *
     * @author yingjun
     * @date 2021年4月28日 下午2:27:56
     */
    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("BbcGoodsInfoVO.SimpleListVO")
    @Accessors(chain = true)
    public static class SimpleListVO implements Serializable {

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private BigDecimal inMemberPointPrice;

        @ApiModelProperty("标签")
        private List<String> tags;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("类目id")
        private String categoryId;


        @ApiModelProperty("商品名称")
        private String goodsName;


        @ApiModelProperty("商品标题")
        private String goodsTitle;


        @ApiModelProperty("商品状态")
        private Integer goodsState;


        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;


        @ApiModelProperty("商品原价")
        private BigDecimal oldPrice;

        @ApiModelProperty("商品图片组")
        private String goodsImage;

    }

    /**
     * 秒杀商品详情
     *
     * @author yingjun
     * @date 2021年4月21日 下午2:17:59
     */
    @Data
    @ApiModel("BbcGoodsInfoVO.SeckillDetailVO")
    @Accessors(chain = true)
    public static class SeckillDetailVO extends DetailVO {

        @ApiModelProperty("秒杀价格")
        private BigDecimal seckillPrice;

        @ApiModelProperty("结束秒杀时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime seckillEndTime;

        @ApiModelProperty("秒杀状态 10=>抢购中,20=>已开抢,30=>即将开抢,50=>已结束,")
        private Integer status;

        /**
         * 普通商品(10,"普通商品"),
         * 积分商品(20,"积分商品"),
         * IN会员商品(30,"IN会员商品");
         */
        @ApiModelProperty("商品类型 10=》普通商品；20=》积分商品；30=》IN会员商品")
        private Integer goodsType;

    }

    @Data
    @ApiModel("BbcGoodsInfoVO.InMemberGoodsVO")
    @Accessors(chain = true)
    public static class InMemberGoodsVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String name;

        @ApiModelProperty("商品内容")
        private PageData<DetailVO> list;

        @ApiModelProperty("封面图")
        private String imageUrl;

        @ApiModelProperty("积分值")
        private Integer telecomsIntegral;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

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
    @ApiModel("BbcGoodsInfoVO.InVipListVO")
    @Accessors(chain = true)
    public static class InVipListVO implements Serializable {

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

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("商品货号")
        private String goodsNo;

        @ApiModelProperty("商品原价")
        private BigDecimal pointPrice;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

        @ApiModelProperty("商品图片组")
        private String goodsImage;

        @ApiModelProperty("商品计价单位")
        private String goodsPriceUnit;

        @ApiModelProperty("会员券后价")
        private BigDecimal inMemberPointPrice;
        
        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品市场价")
        private BigDecimal oldPrice;

    }

    @Data
    @ApiModel("BbcGoodsInfoVO.GoodsListVO")
    public static class GoodsListVO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

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
        
        @ApiModelProperty("商品标签列表")
        private List<String> tags = new ArrayList<>();
        
        

        @ApiModelProperty(value = "idx顺序", hidden = true)
        private Integer idx;

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
    public static class GoodsLabelVO implements Serializable {
        @ApiModelProperty("标签id")
        private String id;

        @ApiModelProperty("标签名称")
        private String labelName;

        @ApiModelProperty("标签颜色")
        private String labelColor;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.MerchantGoodsListVO")
    public static class MerchantGoodsListVO implements Serializable {
        @ApiModelProperty("店铺信息")
        private GoodsShopDetailVO shopDetailVO;

        @ApiModelProperty("商品列表")
        private PageData<GoodsListVO> goodsListVOS;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.DetailVO")
    public static class DetailVO implements Serializable {
    	
        /**
         * 单品或者多规格商品（10 = 单品，20=多规格）
         */
    	@ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;

        @ApiModelProperty("规格列表")
        private List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS;

        @ApiModelProperty(value = "店铺信息")
        private BbcGoodsInfoVO.GoodsShopDetailVO goodsShopDetailVO;

        @ApiModelProperty("多规格商品默认选择规格信息")
        private BbcSkuGoodInfoVO.SkuVO skuVO;

        @ApiModelProperty(value = "店铺客服信息")
        private CommonShopVO.ShopServiceVO shopServiceVO;

        @ApiModelProperty("活动")
        private List<ActivityVOS> activityVOS;

        @ApiModelProperty("优惠券")
        private List<CouponVOS> couponVOS;

        @ApiModelProperty("参数信息")
        private List<AttributeVOS> attributeVOS;

        @ApiModelProperty("标签")
        private List<String> tags;

        @ApiModelProperty("承诺服务")
        private List<PromiseVOS> promiseVOS;

        @ApiModelProperty("商品id")
        private String goodsId;

        @JsonIgnore
        private String id;

        @ApiModelProperty("默认第一个sku商品id")
        private String skuId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty(value = "spu月销量")
        private Integer spuStockNum;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

//        @ApiModelProperty("用户默认收货地址")
//        private String userDefaultAddress;

        @ApiModelProperty("商品收藏状态")
        private Integer favoritesState;

        @ApiModelProperty("单规格的库存")
        private Integer singleSkuStock;

        @ApiModelProperty("办理备注")
        private String remarks;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;

        @ApiModelProperty("兑换类型（虚拟，实物）")
        private Integer exchangeType;

        @ApiModelProperty("视频地址")
        private String videoUrl;

        @ApiModelProperty("销售数量")
        private Integer saleQuantity;

//        @ApiModelProperty("积分兑换数量")
//        private Integer exchangeQuantity;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty(value = "商品市场价/原价")
        private BigDecimal oldPrice;

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;

        @ApiModelProperty("原积分价格")
        private BigDecimal oldPointPrice;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("IN会员价格")
        private BigDecimal inMemberPointPrice;

        @ApiModelProperty("当前登录的用户ID")
        private String userId;

        @ApiModelProperty("用户类型(1-普通用户 2-电信用户)")
        private Integer memberType;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser;
        
        @ApiModelProperty("商品是否可购买 1:是  0：否")
        private Integer isBuy = 1;
        
        @ApiModelProperty("不能够买原因")
        private String buyRemark;
        
        @ApiModelProperty("商品状态")
        private Integer goodsState;

    }

    @Data
    @ApiModel("BbcGoodsInfoVO.AttributeVOS")
    public static class AttributeVOS implements Serializable {

        @ApiModelProperty("参数ID")
        private String id;

        @ApiModelProperty("参数名称")
        private String name;

        @ApiModelProperty("参数值")
        private String value;

        @ApiModelProperty("排序")
        private Integer idx;
    }


    @Data
    @ApiModel("BbcGoodsInfoVO.PromiseVOS")
    public static class PromiseVOS implements Serializable {

        @ApiModelProperty("承诺服务说明ID")
        private String Id;

        @ApiModelProperty("承诺服务说明名称")
        private String name;

        @ApiModelProperty("承诺服务内容-详细描述")
        private String contant;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转页面地址")
        private String jumpUrl;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.CouponVOS")
    public static class CouponVOS implements Serializable {

        @ApiModelProperty("参数名称")
        private String name;

        @ApiModelProperty("参数值")
        private String value;

        @ApiModelProperty("排序")
        private Integer idx;
    }


    @Setter
    @ApiModel("BbcGoodsInfoVO.ActivityVO")
    public static class ActivityVOS implements Serializable {

        @ApiModelProperty("活动枚举(10=优惠卷 20=团购 30=满减 40=满赠 50=满折 60=活动 70=秒杀)")
        private Integer type;

        @ApiModelProperty("活动枚举(10=优惠卷 20=团购 30=满减 40=满赠 50=满折 60=活动 70=秒杀)")
        private String typeText;

        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime endTime;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("活动标签")
        private String label;

        @ApiModelProperty("描述")
        private String describe;

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getTypeText() {
            if (type != null)
                typeText = MarketCheckTypeEnum.getRemarkByCode(type);
            return typeText;
        }

        public void setTypeText(String typeText) {
            this.typeText = typeText;
        }

        public LocalDateTime getStartTime() {
            return startTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.GoodsShopDetailVO")
    public static class GoodsShopDetailVO implements Serializable {
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
    public static class InVIPSpecialAreaVO implements Serializable {
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
    @ApiModel("BbcGoodsInfoVO.IntegralGoodsInfo")
    public static class IntegralGoodsInfo implements Serializable {

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


    @Data
    @ApiModel("BbcGoodsInfoVO.MyIntegrationExchangeVO")
    public static class MyIntegrationExchangeVO implements Serializable {
        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("品牌id")
        @JsonIgnore
        private String brandId;

        @ApiModelProperty("品牌名称")
        private String brandName;

        @ApiModelProperty("积分价格")
        private Double pointPrice;

        @ApiModelProperty("历史积分价格")
        private Double oldPointPrice;

    }

    @Data
    @ApiModel("BbcGoodsInfoVO.RecommendGoodsVO")
    public static class RecommendGoodsVO implements Serializable {

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品标题")
        private String goodsTitle;

        @ApiModelProperty("品牌id")
        @JsonIgnore
        private String brandId;

        @ApiModelProperty("品牌名称")
        private String brandName;

        @ApiModelProperty("积分价格")
        private Double pointPrice;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.CtccGoodsDetailVO")
    public static class CtccGoodsDetailVO implements Serializable {

    	@ApiModelProperty("id")
        private String id;

        @ApiModelProperty("名称")
        private String name;
        
        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品默认图片")
        private String goodsImage;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品排序")
        private Integer idx;

        @ApiModelProperty("商品类别名称")
        private String categoryName;

        @ApiModelProperty("商品状态（10-未上架，20-已上架）")
        private Integer goodsState;

        @ApiModelProperty("品牌名称")
        private String brandName;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("库存")
        private Integer stockQuantity;

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;

        @ApiModelProperty("in会员价格")
        private BigDecimal inMemberPointPrice;

        @ApiModelProperty("实际销量")
        private Integer saleQuantity;

        @ApiModelProperty("规格列表")
        private List<BbcGoodsSpecInfoVO.SpecListVO> specListVOS;

    }

    @Data
    @ApiModel("BbcGoodsInfoVO.SearchHistory")
    public static class SearchHistory implements Serializable {
        @ApiModelProperty("搜索关键字")
        private String keyword;
    }


    //----------------内部服务VO-----------------------------

    @Data
    @ApiModel("BbcGoodsInfoVO.HomeAndShopInnerServiceVO")
    public static class HomeAndShopInnerServiceVO implements Serializable {
        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("sku商品id")
        private String skuId;

        @ApiModelProperty("店铺id")
        private String shopId;

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

        @ApiModelProperty(value = "排序", hidden = true)
        private Integer idx;
        
        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private BigDecimal inMemberPointPrice;

        

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;
        
        @ApiModelProperty("原积分价格")
        private BigDecimal oldPointPrice;

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;
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


        @ApiModelProperty("商品价格")
        private BigDecimal goodsAmount;

        @ApiModelProperty("商品积分价格")
        private BigDecimal goodsPointAmount;

        @ApiModelProperty("折扣价格")
        private BigDecimal discountAmount;

        @ApiModelProperty("折扣积分价格价格")
        private BigDecimal discountPointAmount;

        @ApiModelProperty("应付价格")
        private BigDecimal payableAmount;

        @ApiModelProperty("应付积分价格")
        private BigDecimal payablePointAmount;
        
        /**
         * 单品或者多规格商品（10 = 单品，20=多规格）
         */
    	@ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.InnerGoodsKitSkuVO")
    public static class InnerGoodsKitSkuVO implements Serializable {


    }

    /**
     * IN会员首页
     *
     * @author yingjun
     * @date 2021年5月13日 下午3:47:03
     */
    @Data
    @ApiModel("BbcGoodsInfoVO.InMemberHomeVO")
    public static class InMemberHomeVO implements Serializable {

        @ApiModelProperty("广告位列表")
        private List<BbcSiteAdvertVO.AdvertDetailVO> adverts;

        @ApiModelProperty("优惠券分类列表")
        private List<Map<String, Object>> couponTypes;

        @ApiModelProperty("电信积分")
        private Integer telecomsIntegral;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser;
    }

    @Data
    @ApiModel("BbcGoodsInfoVO.isCollectionGoodsVO")
    public static class isCollectionGoodsVO implements Serializable {
        @ApiModelProperty("是否收藏（0-未收藏 1-已收藏）")
        private Integer isCollect;
    }

    
	@Setter
	@ApiModel("BbcGoodsInfoVO.ListCouponVO")
	public static class ListCouponVO implements Serializable {
		
		@ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
	    private Integer couponType;
		
		@ApiModelProperty("优惠券类型（1-IN会员抵扣券 2-店铺券 3-平台券 4-个人券）")
	    private String couponTypeText;
 
		@ApiModelProperty("0-未领取 1-已领取 2-无需领取")
	    private Integer couponStatus;
	    
		@ApiModelProperty("0-未领取 1-已领取 2-无需领取")
	    private String couponStatusText;

		@ApiModelProperty("优惠券名称")
	    private String couponName;

		@ApiModelProperty("可使用时间")
	    private String useTime;

		@ApiModelProperty("减免金额")
	    private BigDecimal deduction;

		@ApiModelProperty("使用门槛")
	    private BigDecimal useThreshold;
		
		@ApiModelProperty("减免类型 1：积分  2：金额")
	    private Integer deductionType;

		@ApiModelProperty("优惠券ID")
		private String id;

		public Integer getCouponType() {
			return couponType;
		}

		public String getCouponTypeText() {
			if(couponType!=null)
				couponTypeText = GoodsCouponTypeEnum.getRemarkByCode(couponStatus);
			return couponTypeText;
		}

		public Integer getCouponStatus() {
			return couponStatus;
		}

		public String getCouponStatusText() {
			if(couponStatus!=null)
				couponStatusText = GoodsCouponStatusEnum.getRemarkByCode(couponStatus);
			return couponStatusText;
		}

		public String getCouponName() {
			return couponName;
		}

		public String getUseTime() {
			return useTime;
		}

		public BigDecimal getDeduction() {
			return deduction;
		}

		public BigDecimal getUseThreshold() {
			return useThreshold;
		}

		public Integer getDeductionType() {
			return deductionType;
		}

		public String getId() {
			return id;
		}
		
		

	}
	
    @Data
    @ApiModel("BbcGoodsInfoVO.GoodsCtccApiVO")
    public static class GoodsCtccApiVO implements Serializable {
    	
    	private String id;

        /**
         * 商家id
         */
        private String goodId;

        private Integer ctccApi;

        /**
         * 优惠编码
         */
        private String couponCode;

        /**
         * 优惠名称
         */
        private String couponName;

        /**
         * 对应优惠编码
         */
        private String replyCouponCode;

        private String replyCouponName;

        private String attrId;

        private String attrValue;

        private String attrIdPoint;

        private String attrValuePoint;
    }
}