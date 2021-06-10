package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto.PCMerchGoodsFupinDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Starry
 * @since 2020-10-08
 */
public abstract class PCMerchGoodsInfoDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsInfoDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "商品id", hidden = true)
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

        @ApiModelProperty("商品简介")
        private String goodsTitle;

        @ApiModelProperty("商品状态")
        private Integer goodsState;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品原价")
        private BigDecimal oldPrice;


        @ApiModelProperty("商品成本价")
        private BigDecimal costPrice;


        @ApiModelProperty("商品阶梯价")
        private String stepPrice;

        @ApiModelProperty("商品重量")
        private BigDecimal goodsWeight;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

        @ApiModelProperty("电脑端商品描述")
        private String goodsPcDesc;

        @ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;

        @ApiModelProperty(value = "商品计价单位")
        private String goodsPriceUnit;

        @ApiModelProperty("商家条形码")
        private String goodsBarcode;

        @ApiModelProperty("商品有效期")
        private Integer goodsValidDays;

        @ApiModelProperty("商品图片组")
        private String goodsImage;

        @ApiModelProperty(value = "是否显示原价")
        private Integer isShowOldPrice;

        @ApiModelProperty("是否是扶贫商品")
        private Integer isSuportPoorGoods;

        @ApiModelProperty("使用平台")
        private Integer usePlatform;

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

    }

    @Data
    @ApiModel("PCMerchGoodsInfoDTO.AddGoodsETO")
    public static class AddGoodsETO extends ETO {
        @ApiModelProperty(value = "sku商品列表")
        private List<PCMerchSkuGoodInfoDTO.AddETO> etoList = new ArrayList<>();

        @ApiModelProperty(value = "商品拓展属性列表")
        private List<PCMerchGoodsAttributeInfoDTO.ETO> attributeList = new ArrayList<>();

        @ApiModelProperty(value = "商品拓展规格列表")
        private List<PCMerchGoodsSpecInfoDTO.ETO> specList = new ArrayList<>();

        @ApiModelProperty(value = "商品拓展参数列表")
        private List<PCMerchGoodsExtendParamsDTO.ETO> paramsList = new ArrayList<>();

        @ApiModelProperty(value = "运费模板ID")
        private String templateId;

        @ApiModelProperty(value = "减库存方式(10-付款减库存，下单减库存)")
        private Integer stockSubtractType;

        @ApiModelProperty(value = "店铺商品分类")
        private String shopNavigationId;

        @ApiModelProperty(value = "店铺自定义2c类目id")
        private String shop2cNavigationId;

        @ApiModelProperty(value = "库存计数方式")
        private Integer stockChargeWay;

        @ApiModelProperty(value = "spu库存")
        private Integer spuStock;

        @ApiModelProperty(value = "扶贫信息")
        private PCMerchGoodsFupinDTO.ETO fuPinEto;

        @ApiModelProperty(value = "pos店铺spuid")
        private String posSpuId;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;

        @ApiModelProperty("兑换类型（0实物,1虚拟）")
        private Integer exchangeType;

        @ApiModelProperty("视频地址")
        private String videoUrl;

        @ApiModelProperty("in会员优惠券类型（20,30,50,99,200）")
        private Integer inCouponType;

        @ApiModelProperty("服务列表")
        private List<String> goodsServeIdS = new ArrayList<>();

        @ApiModelProperty("商品类型 10：普通商品 20：积分商品 30：IN会员商品")
        private Integer ctccMold;

        @ApiModelProperty("是否包含非库存字段修改")
        private Boolean isIncludeOthers;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoDTO.GoodNoDTO")
    public static class GoodNoDTO extends BaseDTO {

        @ApiModelProperty(value = "spu商品编号")
        private String goodsNo;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private List<String> idList;

        @ApiModelProperty(value = "扶贫商品id", hidden = true)
        private List<String> fuPinGoodsIdList;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoDTO.SettingGoodsTemplateDTO")
    public static class SettingGoodsTemplateDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private List<String> idList;

        @ApiModelProperty(value = "运费模板id")
        private String templateId;
    }

    @Data
    @AllArgsConstructor
    @ApiModel("PCMerchGoodsInfoDTO.EditIdsDTO")
    public static class EditIdsDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;

        @ApiModelProperty(value = "店铺id")
        private String shopId;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoDTO.ExcelGoodsDataETO")
    public static class ExcelGoodsDataETO extends BaseDTO {
        @ApiModelProperty(value = "一级类目", position = 1)
        private String categoryLevel1Name;

        @ApiModelProperty(value = "二级类目", position = 2)
        private String categoryLevel2Name;

        @ApiModelProperty(value = "三级类目", position = 3)
        private String categoryLevel3Name;

        @ApiModelProperty(value = "商品标题", position = 4)
        private String goodsName;

        @ApiModelProperty(value = "商品副标题", position = 5)
        private String goodsTitle;

        @ApiModelProperty(value = "品牌", position = 6)
        private String brandName;

        @ApiModelProperty(value = "sku销售价", position = 7)
        private BigDecimal salePrice;

        @ApiModelProperty(value = "sku原价", position = 8)
        private BigDecimal oldPrice;

        @ApiModelProperty(value = "sku成本价", position = 9)
        private BigDecimal costPrice;

        @ApiModelProperty(value = "sku商品库存", position = 10)
        private Integer stockNum;

        @ApiModelProperty(value = "库存计数方式", position = 11)
        private Integer stockSubtractType;

        @ApiModelProperty(value = "重量", position = 12)
        private BigDecimal goodsWeight;

        @ApiModelProperty(value = "计价单位", position = 13)
        private String chargeUnit;

        @ApiModelProperty(value = "商品有效期", position = 14)
        private Integer goodsValidDays;

        @ApiModelProperty(value = "规格值", position = 15)
        private String specValue;

        @ApiModelProperty(value = "属性值", position = 16)
        private String attributeValue;

        @ApiModelProperty(value = "运费模板名称", position = 17)
        private String templateName;

        @ApiModelProperty(value = "店铺自定义类目名称", position = 18)
        private String shopNavigationName;

        @ApiModelProperty(value = "同一商品标识", position = 19)
        private String goodsNo;

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

        @ApiModelProperty("兑换类型（0实物,1虚拟）")
        private Integer exchangeType;
    }


    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchGoodsInfoDTO.IdsInnerServiceDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdsInnerServiceDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id数组")
        private List<String> goodsIdList;

        @ApiModelProperty(value = "店铺id数组")
        private List<String> shopIdList;

        @ApiModelProperty(value = "查询类型(10=按商品id数组，20=按店铺id数组）")
        private Integer queryType;
    }

}
