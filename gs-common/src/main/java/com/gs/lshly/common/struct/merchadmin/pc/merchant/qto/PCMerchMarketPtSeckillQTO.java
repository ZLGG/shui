package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hanly
 * @date 2021年6月10日 下午2:16:45
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillQTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("活动状态 ")
        private Integer state;
        @ApiModelProperty("开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime endTime;
        @ApiModelProperty("关键字")
        private String keyWord;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillQTO.IdQTO")
    @Accessors(chain = true)
    public static class IdQTO extends BaseQTO {
        @ApiModelProperty("秒杀活动id")
        private String id;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillQTO.SpuQTO")
    @Accessors(chain = true)
    public static class SpuQTO extends BaseQTO {

        @ApiModelProperty("时间端id")
        private String id;

        @ApiModelProperty("商品类型")
        private Integer goodsType;

        @ApiModelProperty("关键字(商品类目id,商品名称，商品编号)")
        private String keyWord;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillQTO.SpuQTO")
    @Accessors(chain = true)
    public static class SkuQTO extends BaseQTO {

        @ApiModelProperty("当前参加活动的spu表Id")
        private String spuId;

        @ApiModelProperty("规格")
        private String specsValue;

        @ApiModelProperty("剩余库存")
        private Integer seckillInventory;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillQTO.AllSpuQTO")
    @Accessors(chain = true)
    public static class AllSpuQTO extends BaseQTO {
        @ApiModelProperty("已参加的商品id")
        private List<String> spuIdList;
        @ApiModelProperty("商品类型")
        private Integer goodsType;
        @ApiModelProperty("关键字")
        private String keyWord;
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @ApiModel("PCMerchMarketPtSeckillQTO.AllSkuQTO")
    @Accessors(chain = true)
    public static class AllSkuQTO extends BaseQTO {
        @ApiModelProperty("已参加的商品skuId")
        private List<String> spuIdList;
    }
}
