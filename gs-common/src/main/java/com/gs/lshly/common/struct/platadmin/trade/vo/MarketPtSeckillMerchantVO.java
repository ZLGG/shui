package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author hanly
 * @date 2021年5月31日 上午10:38:40
 */
public class MarketPtSeckillMerchantVO implements Serializable {
    @Data
    @ApiModel("MarketPtSeckillMerchantVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("所属商家")
        private String merchantName;

        @ApiModelProperty("秒杀名称")
        private String name;

        @ApiModelProperty("活动状态")
        private Integer activityState;

        @ApiModelProperty("开售开始时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillStartTime;

        @ApiModelProperty("开售结束时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private LocalDateTime seckillEndTime;

        @ApiModelProperty("审核状态(10=审核 20=未审核 30=审核驳回)")
        private Integer state;

    }
}
