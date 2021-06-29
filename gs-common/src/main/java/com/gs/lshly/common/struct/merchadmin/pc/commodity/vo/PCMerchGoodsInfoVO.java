package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gs.lshly.common.annotation.ExportProperty;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.vo.PCMerchGoodsFupinVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsServeVO;
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
 * @since 2020-10-08
 */
public abstract class PCMerchGoodsInfoVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("pos店铺商品id")
        private String posSpuId;

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

        @ApiModelProperty("商家条形码")
        private String goodsBarcode;


        @ApiModelProperty("商品有效期")
        private Integer goodsValidDays;


        @ApiModelProperty("商品图片组")
        private String goodsImage;

        @ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;

        @ApiModelProperty("是否是扶贫商品")
        private Integer isSuportPoorGoods;

        @ApiModelProperty(value = "商品计价单位")
        private String goodsPriceUnit;

        @ApiModelProperty("使用平台")
        private Integer usePlatform;

        @ApiModelProperty(value = "是否显示原价")
        private Integer isShowOldPrice;

        @ApiModelProperty("操作人")
        private String operator;

        @ApiModelProperty(value = "发布时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime publishTime;

        @ApiModelProperty("积分价格")
        private BigDecimal pointPrice;

        @ApiModelProperty("原积分价格")
        private BigDecimal oldPointPrice;

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

        @ApiModelProperty("商品类型 10：普通商品 20：积分商品 30：IN会员商品")
        private Integer ctccMold;

        @ApiModelProperty("是否包含非库存字段修改")
        private Boolean isIncludeOthers;

        @ApiModelProperty("结算价格")
        private BigDecimal settlementPrice;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.GoodsNameVO")
    public static class GoodsNameVO implements Serializable {
        @ApiModelProperty(value = "商品名称")
        private String goodsName;

        @ApiModelProperty(value = "商品图片")
        private String goodsImage;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.GoodsActiveVO")
    public static class GoodsActiveVO implements Serializable {
        @ApiModelProperty(value = "商品id")
        private String id;

        @ApiModelProperty(value = "商品名称")
        private String goodsName;

        @ApiModelProperty(value = "商品图片")
        private String goodsImage;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.SkuActiveVO")
    public static class SkuActiveVO implements Serializable {
        @ApiModelProperty(value = "商品skuId")
        private String skuId;

        @ApiModelProperty(value = "商品名称")
        private String goodsName;

        @ApiModelProperty(value = "商品图片")
        private String goodsImage;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.SkuActivePageVO")
    public static class SkuActivePageVO implements Serializable {
        @ApiModelProperty(value = "商品skuId")
        private String skuId;

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty(value = "商品名称")
        private String goodsName;

        @ApiModelProperty(value = "商品图片")
        private String skuImg;

        @ApiModelProperty("商品售价")
        private BigDecimal skuSalePrice;

        @ApiModelProperty("规格值")
        private String specsValue;

        @ApiModelProperty("规格值id")
        private String specsKey;

        @ApiModelProperty("商品状态 10=未上架 20=上架 30=待审核 40=审核失败")
        private Integer state;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.GoodsIdVO")
    public static class GoodsIdVO implements Serializable {
        @ApiModelProperty(value = "商品id")
        public String goodsId;
    }


    @Data
    @ApiModel("PCMerchGoodsInfoVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty(value = "sku列表")
        private List<PCMerchSkuGoodInfoVO.DetailVO> detailVOList;

        @ApiModelProperty(value = "店铺2b分类")
        private String shopCategoryId;

        @ApiModelProperty(value = "店铺2c分类")
        private String shop2cCategoryId;

        @ApiModelProperty(value = "运费模板Id")
        private String templateId;

        @ApiModelProperty(value = "库存总数")
        public Integer spuStockNum;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.GoodsStateCountVO")
    public static class GoodsStateCountVO implements Serializable {

        @ApiModelProperty(value = "待上架商品数")
        public Integer waitForOnNum;

        @ApiModelProperty(value = "已上架商品数")
        public Integer hasOnNum;

        @ApiModelProperty(value = "审核中商品数")
        public Integer waitForAduitNum;

        @ApiModelProperty(value = "已审核商品数")
        public Integer hasAduitNum;

        @ApiModelProperty(value = "草稿箱商品数")
        public Integer waitEditNum;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.EditDetailVO")
    public static class EditDetailVO extends ListVO {
        @ApiModelProperty(value = "sku商品列表")
        private List<PCMerchSkuGoodInfoVO.DetailVO> skuVoList;

        @ApiModelProperty(value = "商品拓展属性列表")
        private List<PCMerchGoodsAttributeInfoVO.ListVO> attributeList;

        @ApiModelProperty(value = "商品拓展规格列表")
        private List<PCMerchGoodsSpecInfoVO.ListVO> specList;

        @ApiModelProperty(value = "商品拓展参数列表")
        private List<PCMerchGoodsExtendParamsVO.ListVO> paramsList;

        @ApiModelProperty(value = "运费模板ID")
        private String templateId;

        @ApiModelProperty(value = "店铺自定义类目id")
        private String shopNavigationId;

        @ApiModelProperty(value = "店铺2c自定义类目id")
        private String shop2cNavigationId;

        @ApiModelProperty(value = "库存计数方式xx")
        private Integer stockChargeWay;

        @ApiModelProperty(value = "减库存方式(10-付款减库存，下单减库存)")
        private Integer stockSubtractType;

        @ApiModelProperty(value = "spu库存")
        private Integer spuStock;

        @ApiModelProperty("扶贫信息")
        private PCMerchGoodsFupinVO.DetailVO fuPinDetailVO;

        @ApiModelProperty(value = "服务数据")
        private List<String> goodsServeList;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.SpuListVO")
    public static class SpuListVO extends ListVO {
        @ApiModelProperty(value = "是否设置了模板")
        private Boolean hasTemplate;

        @ApiModelProperty(value = "修改时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime udate;

        @ApiModelProperty(value = "申请类型 1:新增商品 2:更新商品")
        private Integer applyType;

        @ApiModelProperty(value = "审核结果 1:通过 2:不通过")
        private Integer aduitResult;

        @ApiModelProperty(value = "结果反馈")
        private String refuseRemark;

        @ApiModelProperty(value = "运费模版名")
        private String templateName;

        @ApiModelProperty(value = "库存数量")
        private List<PCMerchGoodsInfoVO.SkuStockNum>  stockNumList;

        @ApiModelProperty(value = "提交时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime commitTime;

        @ApiModelProperty(value = "审核时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime aduitTime;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.StockAlarmGoodsVO")
    public static class StockAlarmGoodsVO extends SpuListVO {

    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.ExcelGoodsDataVO")
    public static class ExcelGoodsDataVO implements Serializable {
        @ApiModelProperty(value = "一级类目", position = 1)
        private String categoryLevel1Name;

        @ApiModelProperty(value = "二级类目", position = 2)
        private String categoryLevel2Name;

        @ApiModelProperty(value = "三级类目", position = 3)
        private String categoryLevel3Name;

        @ApiModelProperty(value = "店铺分类", position = 4)
        private String shopNavigation;

        @ApiModelProperty(value = "商品标题", position = 5)
        private String goodsName;

        @ApiModelProperty(value = "商品副标题", position = 6)
        private String goodsTitle;

        @ApiModelProperty(value = "品牌", position = 7)
        private String brandName;

        @ApiModelProperty(value = "商品货号", position = 8)
        private String goodsNo;

        @ApiModelProperty(value = "条形码", position = 9)
        private String goodsBarcode;

        @ApiModelProperty(value = "发布平台", position = 10)
        private String publishPlatform;

        @ApiModelProperty(value = "销售价", position = 11)
        private BigDecimal salePrice;

        @ApiModelProperty(value = "库存", position = 12)
        private Integer stockNum;

        @ApiModelProperty(value = "库存计数方式", position = 13)
        private String stockSubtractType;

        @ApiModelProperty(value = "原价", position = 14)
        private BigDecimal oldPrice;

        @ApiModelProperty(value = "是否显示原价", position = 15)
        private String showOrNoOldPrice;

        @ApiModelProperty(value = "成本价", position = 16)
        private BigDecimal costPrice;

        @ApiModelProperty(value = "重量", position = 17)
        private BigDecimal goodsWeight;

        @ApiModelProperty(value = "计价单位", position = 18)
        private String chargeUnit;

        @ApiModelProperty(value = "运费模板名称", position = 19)
        private String templateName;

        @ApiModelProperty(value = "规格值", position = 20)
        private String specValue;

        @ApiModelProperty(value = "商品有效期", position = 21)
        private Integer goodsValidDays;

        @ApiModelProperty("办理备注")
        private String remarks;

        @ApiModelProperty("是否是积分商品")
        private String isPointGood;

        @ApiModelProperty("积分价格")
        private Double pointPrice;

        @ApiModelProperty("是否是in会员礼品")
        private String isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private Double inMemberPointPrice;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;

        @ApiModelProperty("兑换类型（虚拟，实物）")
        private Integer exchangeType;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.ImportGoodsDataVO")
    public static class ImportGoodsDataVO implements Serializable {
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
        private String salePrice;

        @ApiModelProperty(value = "sku原价", position = 8)
        private String oldPrice;

        @ApiModelProperty(value = "sku成本价", position = 9)
        private String costPrice;

        @ApiModelProperty(value = "sku商品库存", position = 10)
        private String stockNum;

        @ApiModelProperty(value = "库存计数方式", position = 11)
        private String stockSubtractType;

        @ApiModelProperty(value = "重量", position = 12)
        private String goodsWeight;

        @ApiModelProperty(value = "计价单位", position = 13)
        private String chargeUnit;

        @ApiModelProperty(value = "商品有效期", position = 14)
        private String goodsValidDays;

        @ApiModelProperty(value = "规格值", position = 15)
        private String specValue;

        @ApiModelProperty(value = "属性值", position = 16)
        private String attributeValue;

        @ApiModelProperty(value = "运费模板名称", position = 17)
        private String templateName;

        @ApiModelProperty(value = "店铺自定义2b类目名称", position = 18)
        private String shopNavigationName;

        @ApiModelProperty(value = "店铺自定义2c类目名称", position = 19)
        private String shopNavigation2cName;

        @ApiModelProperty(value = "同一商品标识", position = 20)
        private String goodsNo;

        @ApiModelProperty(value = "办理备注", position = 21)
        private String remarks;

        @ApiModelProperty(value = "是否是积分商品", position = 22)
        private String isPointGood;

        @ApiModelProperty(value = "积分价格", position = 23)
        private String pointPrice;

        @ApiModelProperty(value = "是否是in会员礼品", position = 24)
        private String isInMemberGift;

        @ApiModelProperty(value = "in会员积分价格", position = 25)
        private String inMemberPointPrice;

        @ApiModelProperty(value = "出售类型（0普通，1活动）", position = 26)
        private String saleType;

        @ApiModelProperty(value = "信天游产品号", position = 27)
        private String thirdProductId;

        @ApiModelProperty(value = "兑换类型（0实物，1虚拟）", position = 28)
        private String exchangeType;
    }


    @Data
    @ApiModel("PCMerchGoodsInfoVO.ShopFloorCommodityVO")
    public static class ShopFloorCommodityVO implements Serializable {

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品类目名称")
        private String goodsCategoryName;

        @ApiModelProperty("商品品牌")
        private String goodsBrand;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;
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

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;

        @ApiModelProperty("兑换类型（0实物,1虚拟）")
        private Integer exchangeType;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.ShopNavigationCommodityVO")
    public static class ShopNavigationCommodityVO implements Serializable {

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;
    }


    @Data
    @ApiModel("PCMerchGoodsInfoVO.ShopCategoryGoodsVO")
    public static class ShopCategoryGoodsVO implements Serializable {
        @ApiModelProperty("分类id")
        private String levelId;

        @ApiModelProperty("分类父id")
        private String levelParentId;

        @ApiModelProperty("分类名称")
        private String levelName;

        @ApiModelProperty("分类商品列表")
        private List<ShopLevel2CategoryGoodsVO> level2CategoryGoodsVOS = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.ShopLevel2CategoryGoodsVO")
    public static class ShopLevel2CategoryGoodsVO implements Serializable {
        @ApiModelProperty("分类2id")
        private String level2Id;

        @ApiModelProperty("分类2父id")
        private String level2ParentId;

        @ApiModelProperty("分类2名称")
        private String level2Name;

        @ApiModelProperty("分类商品列表")
        private List<ShopLevel3CategoryVO> level3CategoryVOS = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.ShopCategoryVO")
    public static class ShopLevel3CategoryVO implements Serializable {
        @ApiModelProperty("分类3id")
        private String level3Id;

        @ApiModelProperty("分类3父id")
        private String level3ParentId;

        @ApiModelProperty("分类3名称")
        private String level3Name;

        @ApiModelProperty("类目下的sku商品信息")
        private List<CategorySkuGoodsVO> categorySkuGoodsVOS = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.CategorySkuGoodsVO")
    public static class CategorySkuGoodsVO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品skuId")
        private String skuId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;
    }


    @Data
    @ApiModel("PCMerchGoodsInfoVO.InnerServiceGoodsVO")
    public static class InnerServiceGoodsVO implements Serializable {

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品名称")
        private String goodsName;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.InnerServiceGoodsInfoVO")
    public static class InnerServiceGoodsInfoVO extends ListVO {

        @ApiModelProperty(value = "sku列表")
        private List<PCMerchSkuGoodInfoVO.ListVO> skuList;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.HomeCountGoodsVO")
    public static class HomeCountGoodsVO implements Serializable {
        @ApiModelProperty("未上架商品数量")
        private Integer underGoodsNum;

        @ApiModelProperty("审核失败商品数量")
        private Integer checkFaildNum;

        @ApiModelProperty("商品咨询数量")
        private Integer qaGoodsNum;

        @ApiModelProperty("库存预警商品数量")
        private Integer stockAlarmNum;
    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.SkuIdByGoodsNoVO")
    @Accessors(chain = true)
    public static class SkuIdByGoodsNoVO implements Serializable {
        @ApiModelProperty("skuId")
        private String skuId;

        @ApiModelProperty("商品ID")
        private String goodsId;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.SkuIdByGoodsNoPosVO")
    @Accessors(chain = true)
    public static class SkuIdByGoodsNoPosVO implements Serializable {
        @ApiModelProperty("skuId")
        private String skuId;

        @ApiModelProperty("商品ID")
        private String goodsId;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoVO.SkuStockNum")
    @Accessors(chain = true)
    public static class SkuStockNum implements Serializable {
        @ApiModelProperty("规格")
        private String specsValue;

        @ApiModelProperty("库存数量")
        private Integer stockNum;

        @ApiModelProperty("skuId")
        private String skuId;

    }
}
