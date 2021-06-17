package com.gs.lshly.common.struct.bbc.commodity.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
* @author Starry
* @since 2020-10-23
*/
@SuppressWarnings("serial")
public abstract class BbcGoodsInfoDTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsInfoDTO.ETO")
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

    }

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsInfoDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;
    }
    
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsInfoDTO.SeckillIdDTO")
    @AllArgsConstructor
    public static class SeckillIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String id;
        
        @ApiModelProperty(value = "秒杀活动spu表ID")
        private String marketPtSeckillGoodsSpuId;
    }
    
    /**
     * 跟据商品分类查询商品信息，并指字商品数量
     *
     * 
     * @author yingjun
     * @date 2021年4月28日 上午11:55:07
     */
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsInfoDTO.CategoryIdCountDTO")
    public static class CategoryIdCountDTO extends BaseDTO {

		@ApiModelProperty(value = "商品分类id")
        private String categoryId;
    
        @ApiModelProperty(value = "商品数量")
        private Integer count;
    }

}
