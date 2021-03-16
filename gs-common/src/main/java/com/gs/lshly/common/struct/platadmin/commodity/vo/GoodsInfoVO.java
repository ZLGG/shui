package com.gs.lshly.common.struct.platadmin.commodity.vo;

import com.gs.lshly.common.struct.common.CommonShopVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import com.gs.lshly.common.struct.platadmin.commodityfupin.vo.GoodsFupinVO;
import com.gs.lshly.common.struct.platadmin.merchant.vo.ShopVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author Starry
 * @Date 15:34 2020/10/14
 */
public abstract class GoodsInfoVO implements Serializable {

    @Data
    @ApiModel("GoodsInfoVO.ListVO")
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


        @ApiModelProperty("是否是扶贫商品")
        private Integer isSuportPoorGoods;


        @ApiModelProperty("使用平台")
        private Integer usePlatform;

        @ApiModelProperty(value = "是否显示原价")
        private Integer isShowOldPrice;

        @ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;

        @ApiModelProperty(value = "商品计价单位")
        private String goodsPriceUnit;

        @ApiModelProperty("操作人")
        private String operator;

        @ApiModelProperty(value = "发布时间")
        private LocalDateTime publishTime;

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

    }

    @Data
    @ApiModel("GoodsInfoVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty(value = "一级类目")
        private String categoryLevel1;

        @ApiModelProperty(value = "二级类目")
        private String categoryLevel2;

        @ApiModelProperty(value = "三级类目")
        private String categoryLevel3;

        @ApiModelProperty(value = "店铺2b一级分类")
        private String shopLevel1Name;

        @ApiModelProperty(value = "店铺2b二级分类")
        private String shopLevel2Name;

        @ApiModelProperty(value = "店铺2c一级分类")
        private String shop2cLevel1Name;

        @ApiModelProperty(value = "店铺2c二级分类")
        private String shop2cLevel2Name;

        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "运费模板名称")
        private String templateName;

        @ApiModelProperty(value = "库存计数方式")
        private Integer stockChargeWays;

        @ApiModelProperty(value = "库存")
        private Integer spuStockNum;

        @ApiModelProperty(value = "计价单位")
        private String chargeUint;

        @ApiModelProperty(value = "sku列表")
        private List<SkuGoodsInfoVO.DetailVO> list;

        @ApiModelProperty(value = "店铺名称")
        private String shopName;

        @ApiModelProperty(value = "审核记录列表")
        private List<GoodsAuditRecordVO.ListVO> listAuditRecord;

        @ApiModelProperty(value = "扶贫商品信息")
        private GoodsFupinVO.DetailVO fupinInfo;
    }

    @Data
    @ApiModel("GoodsInfoVO.SpuListVO")
    public static class SpuListVO extends ListVO {

        @ApiModelProperty(value = "所属店铺")
        private String shopName;

        @ApiModelProperty(value = "店铺类型")
        private Integer shopType;

        @ApiModelProperty(value = "商品品牌")
        private String brandName;

        @ApiModelProperty(value = "类目名称")
        private String categoryName;

        @ApiModelProperty(value = "商品标签")
        private List<GoodsLabelVO.ListVO> labels;
    }

    @Data
    @ApiModel("GoodsInfoVO.GoodsNameVO")
    public static class GoodsNameVO implements Serializable {
        @ApiModelProperty(value = "商品名称")
        public String goodsName;

        @ApiModelProperty("商品图片组")
        private String goodsImage;
    }

    @Data
    @ApiModel("GoodsInfoVO.BindCategoryGoodsVO")
    public static class BindCategoryGoodsVO implements Serializable{
        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品名称")
        private String goodsName;

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

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;
    }

    @Data
    @ApiModel("GoodsInfoVO.ShopFloorCommodityVO")
    public static class ShopFloorCommodityVO implements Serializable{

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
    }

    @Data
    @ApiModel("GoodsInfoVO.FupinFloorCommodityVO")
    public static class FupinFloorCommodityVO implements Serializable{

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
    @ApiModel("GoodsInfoVO.InnerServiceGoodsVO")
    public static class InnerServiceGoodsVO implements Serializable{

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商品名称")
        private String goodsName;

    }

    @Data
    @ApiModel("GoodsInfoVO.InnerServiceGoodsInfoVO")
    public static class InnerServiceGoodsInfoVO extends ListVO {

        @ApiModelProperty(value = "sku列表")
        private List<SkuGoodsInfoVO.ListVO> skuList;

    }

}
