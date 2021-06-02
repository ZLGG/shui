package com.gs.lshly.common.struct.platadmin.trade.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author hanly
 * @date 2021年6月2日 上午11:21:54
 */
@SuppressWarnings("serial")
public abstract class MarketPtSeckillTimeQuantumDTO implements Serializable {

    @EqualsAndHashCode(callSuper = false)
    @Data
    @ApiModel("MarketPtSeckillTimeQuantumDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {
        @ApiModelProperty("时间段id")
        private Integer id;
        @ApiModelProperty("秒杀时间段名称")
        private String timeQuantumName;

        @ApiModelProperty("开始时间")
        private String startTime;

        @ApiModelProperty("结束时间")
        private String endTime;
    }

    @Data
    @ApiModel("MarketPtSeckillTimeQuantumDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "时间段编号")
        private String id;
    }
}
