package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 下午2:28:35
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillGoodsSpuDTO implements Serializable {

	@Data
	@EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchMarketPtSeckillGoodsSpuDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("秒杀ID")
        private String seckillId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("秒杀名称")
        private String name;
        /**
         * sku秒杀价
         */
        private BigDecimal seckillSalePrice;
        @ApiModelProperty("标签")
        private String label;

        @ApiModelProperty("描述")
        private String seckillDescribe;

        @ApiModelProperty("商品ID")
        private String goodsId;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillGoodsSpuDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    //用于报名
    @Data
    @ApiModel("PCMerchMarketPtSeckillGoodsSpuDTO.Sign")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sign extends BaseDTO {
        @ApiModelProperty(value = "秒杀ID")
        private String  seckillId;
        @ApiModelProperty(value = "秒杀商品")
        private List<SignGoods> goodsAll;
        @ApiModelProperty(value = "秒杀商品规格价格")
        private List<SignGoodsSku> goodsSPUAll;
    }
    @Data
    @ApiModel("PCMerchMarketPtSeckillGoodsSpuDTO.SignGoods")
    @AllArgsConstructor
    public static class SignGoods implements Serializable{
        @ApiModelProperty(value = "商品ID")
        private String  goodsId;
        @ApiModelProperty(value = "秒杀价")
        private BigDecimal seckillSalePrice;


    }
    @Data
    @ApiModel("PCMerchMarketPtSeckillGoodsSpuDTO.SignGoodsSku")
    @AllArgsConstructor
    public static class SignGoodsSku implements Serializable{
        @ApiModelProperty(value = "商品ID")
        private String goodsId ;

        @ApiModelProperty(value = "商家报名SPUID")
        private String goodsSpuItemId;

        @ApiModelProperty(value = "skuID")
        private String skuId;

        @ApiModelProperty(value = "秒杀价")
        private BigDecimal seckillSalePrice;
    }

}
