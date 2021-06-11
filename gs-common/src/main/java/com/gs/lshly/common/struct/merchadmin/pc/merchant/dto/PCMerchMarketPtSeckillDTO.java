package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @author yingjun
 * @date 2021年5月8日 下午2:22:34
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillDTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.SpuIdETO")
    @Accessors(chain = true)
    public static class SpuIdETO extends BaseDTO {

        @ApiModelProperty("报名的商品id")
        private List<String> spuIdList;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.SkuIdETO")
    @Accessors(chain = true)
    public static class SkuIdETO extends BaseDTO {

        @ApiModelProperty("报名的sku商品的skuid")
        private List<String> skuIdList;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.AddSeckillGoodsETO")
    @Accessors(chain = true)
    public static class AddSeckillGoodsETO extends BaseDTO {

        @ApiModelProperty("报名的商品spu表id")
        private List<String> spuIdList;

        @ApiModelProperty("要报名的sku商品信息")
        private List<PCMerchMarketPtSeckillDTO.SeckillSkuGoodsETO> seckillSkuGoodsETOList;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.SeckillSkuGoodsETO")
    @Accessors(chain = true)
    public static class SeckillSkuGoodsETO extends BaseDTO {
        @ApiModelProperty("商品报名skuId")
        private String id;

        @ApiModelProperty("商品skuId")
        private String skuId;

        @ApiModelProperty("秒杀价")
        private BigDecimal seckillSaleSkuPrice;

        @ApiModelProperty("秒杀数量")
        private Integer seckillQuantity;

        @ApiModelProperty("剩余库存")
        private Integer seckillInventory;

        @ApiModelProperty("限购数量")
        private Integer restrictQuantity;
    }
}
