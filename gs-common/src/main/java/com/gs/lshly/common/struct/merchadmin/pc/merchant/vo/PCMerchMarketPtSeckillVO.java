package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.DetailVO")
    public static class QuanTumVO extends ListVO {
        @ApiModelProperty("场次时间")
        private List<MarketPtSeckillVO.SessionVO> sessionTime;


    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.SessionVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class SessionVO implements Serializable {
        @ApiModelProperty("场次id")
        private String id;

        @ApiModelProperty("场次开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime startTime;

        @ApiModelProperty("场次结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime endTime;
    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.GoodsInfoVO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GoodsInfoVO implements Serializable {
        @ApiModelProperty("商品spuId,商品编号")
        private String id;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品类型")
        private Integer goodsType;

        @ApiModelProperty("原价")
        private BigDecimal salePrice;
    }
}
