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
 * @date 2021年6月2日 上午10:38:40
 */
@SuppressWarnings("serial")
public class MarketPtSeckillTimeQuantumVO implements Serializable {

    @Data
    @ApiModel("MarketPtSeckillTimeQuantumVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        @ApiModelProperty("编号")
        private Integer id;

        @ApiModelProperty("秒杀时间段名称")
        private String timeQuantumName;

        @ApiModelProperty("开始时间")
        private String startTime;

        @ApiModelProperty("结束时间")
        private String endTime;

    }
}
