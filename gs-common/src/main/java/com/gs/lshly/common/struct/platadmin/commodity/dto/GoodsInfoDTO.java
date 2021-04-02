package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 17:27 2020/10/14
 */
public abstract class GoodsInfoDTO implements Serializable {


    @Data
    @ApiModel("GoodsInfoDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "商品id",hidden = true)
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

        @ApiModelProperty(value = "商品计价单位")
        private String goodsPriceUnit;

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

        @ApiModelProperty(value = "是否显示原价")
        private Integer isShowOldPrice;

        @ApiModelProperty("是否是扶贫商品")
        private Integer isSuportPoorGoods;

        @ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;

        @ApiModelProperty("使用平台")
        private Integer usePlatform;

    }


    @Data
    @ApiModel("GoodsInfoDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;
    }

    @Data
    @ApiModel("GoodsInfoDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private List<String> idList;
    }

    @Data
    @ApiModel("GoodsInfoDTO.CheckGoodsDTO")
    public static class CheckGoodsDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;

        @ApiModelProperty(value = "审核状态")
        private Integer state;

        @ApiModelProperty(value = "审核拒绝原因")
        private String refuseRemark;
    }

    @Data
    @ApiModel("GoodsInfoDTO.CheckGoodsBatchesDTO")
    public static class CheckGoodsBatchesDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id列表")
        private List<String> idList = new ArrayList<>();

        @ApiModelProperty(value = "审核状态")
        private Integer state;

        @ApiModelProperty(value = "审核拒绝原因")
        private String refuseRemark;
    }

    @Data
    @Accessors(chain = true)
    @ApiModel("GoodsInfoDTO.IdsInnerServiceDTO")
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
