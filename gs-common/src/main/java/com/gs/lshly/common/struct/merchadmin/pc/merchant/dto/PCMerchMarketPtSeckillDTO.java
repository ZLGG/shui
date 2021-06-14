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
        @ApiModelProperty("秒杀活动id")
        private String seckillId;
        @ApiModelProperty("秒杀场次id")
        private String timeQuantumId;
        @ApiModelProperty("报名的商品spu信息")
        private List<PCMerchMarketPtSeckillDTO.AddSpuList> spuList;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.AddSpuList")
    @Accessors(chain = true)
    public static class AddSpuList extends BaseDTO {
        @ApiModelProperty("商品spuId")
        private String goodsId;
        @ApiModelProperty("商品类型")
        private Integer goodsType;
        @ApiModelProperty("商品名称")
        private String goodsName;
    }


    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.SpuIdList")
    @Accessors(chain = true)
    public static class SpuIdList extends BaseDTO {

        @ApiModelProperty("报名的sku商品的skuid")
        private List<String> spuIdList;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.EndGoods")
    @Accessors(chain = true)
    public static class EndGoods extends BaseDTO {
        @ApiModelProperty("要报名的sku商品信息")
        private List<PCMerchMarketPtSeckillDTO.AddSeckillGoodsETO> addSeckillGoodsETOList;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.AddSeckillGoodsETO")
    @Accessors(chain = true)
    public static class AddSeckillGoodsETO extends BaseDTO {
        @ApiModelProperty("已报名的spuId")
        private String goodsSpuItemId;

        @ApiModelProperty("要报名的sku商品信息")
        private List<PCMerchMarketPtSeckillDTO.SeckillSkuGoodsETO> seckillSkuGoodsETOList;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillDTO.SeckillSkuGoodsETO")
    @Accessors(chain = true)
    public static class SeckillSkuGoodsETO extends BaseDTO {
        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品skuId")
        private String skuId;

        @ApiModelProperty("秒杀价")
        private BigDecimal seckillSaleSkuPrice;

        @ApiModelProperty("秒杀数量")
        private Integer seckillQuantity;

        @ApiModelProperty("限购数量")
        private Integer restrictQuantity;
    }
}
