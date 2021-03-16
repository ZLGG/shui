package com.gs.lshly.common.struct.platadmin.stock.vo;
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
public abstract class StockLogisticsWatchVO implements Serializable {

    @Data
    @ApiModel("StockLogisticsWatchVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;


        @ApiModelProperty("参数键名")
        private String paramKey;


        @ApiModelProperty("参数值")
        private String paramValue;

        @ApiModelProperty("使用状态")
        private Integer state;




    }

    @Data
    @ApiModel("StockLogisticsWatchVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
