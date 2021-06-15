package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.response.PageData;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hanly
 * @date 2021年6月10日 上午10:32:05
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("活动id")
        private String id;

        @ApiModelProperty("活动名称")
        private String name;

        @ApiModelProperty("活动状态")
        private Integer state;

        @ApiModelProperty("报名开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime signStartTime;

        @ApiModelProperty("报名结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime signEndTime;

        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillStartTime;

        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillEndTime;

        @ApiModelProperty("报名状态")
        private Integer applyState;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.SessionVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SessionVO implements Serializable {
        @ApiModelProperty("场次id")
        private String id;

        @ApiModelProperty("活动id")
        private String seckillId;

        @ApiModelProperty("场次开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("场次结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime endTime;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.SpuVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SpuVO implements Serializable {

        @ApiModelProperty("已选择的数量")
        private Integer count;
        @ApiModelProperty("商品信息集合")
        PageData<AllSpuVO> allSpuVOList;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.AllSpuVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AllSpuVO implements Serializable {
        @ApiModelProperty("商品编号")
        private String id;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品类型")
        private Integer goodsType;

        @ApiModelProperty("商品一级类目名称")
        private String categoryName;

        @ApiModelProperty("spu原价")
        private BigDecimal salePrice;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.AllSkuVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AllSkuVO implements Serializable {
        @ApiModelProperty("商品skuId")
        private String skuId;

        @ApiModelProperty("规格")
        private String specsValue;

        @ApiModelProperty("sku原价")
        private BigDecimal saleSkuPrice;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.SeckillGoodsInfoVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SeckillGoodsInfoVO implements Serializable {
        @ApiModelProperty("商品报名spuId")
        private String spuId;

        @ApiModelProperty("是否被选择(10:已选择,20:未选择)")
        private Integer choose;

        @ApiModelProperty("商品编号")
        private String goodsId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品一级类目名称")
        private String categoryName;

        @ApiModelProperty("商品类型")
        private Integer goodsType;

        @ApiModelProperty("点击编辑的sku信息")
        private List<PCMerchMarketPtSeckillVO.SkuGoodsInfoVO> spuGoodsInfoVOList;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.SpuGoodsInfoVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SkuGoodsInfoVO implements Serializable {
        @ApiModelProperty("商家报名sku的Id")
        private String seckillSkuId;

        @ApiModelProperty("商品sku的Id")
        private String skuId;

        @ApiModelProperty("规格")
        private String specsValue;

        @ApiModelProperty("审核状态")
        private Integer state;

        @ApiModelProperty("原价")
        private BigDecimal saleSkuPrice;

        @ApiModelProperty("秒杀价")
        private BigDecimal seckillSaleSkuPrice;

        @ApiModelProperty("秒杀数量")
        private Integer seckillQuantity;

        @ApiModelProperty("剩余库存")
        private Integer seckillInventory;

        @ApiModelProperty("限购数量")
        private Integer restrictQuantity;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillVO.BrandAndCategry")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BrandAndCategry implements Serializable{
        @ApiModelProperty("品牌id")
        private String brandId;
        @ApiModelProperty("类目id")
        private String categoryId;
    }
}
