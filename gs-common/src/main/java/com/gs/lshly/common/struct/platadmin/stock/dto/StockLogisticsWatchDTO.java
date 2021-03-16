package com.gs.lshly.common.struct.platadmin.stock.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zzg
* @since 2020-10-24
*/
public abstract class StockLogisticsWatchDTO implements Serializable {

    @Data
    @ApiModel("StockLogisticsWatchDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;

        @ApiModelProperty("参数键名")
        private String paramKey;

        @ApiModelProperty("参数值")
        private String paramValue;

        @ApiModelProperty("启用状态")
        private Integer state;
    }

    @Data
    @ApiModel("StockLogisticsWatchDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;
    }


}
