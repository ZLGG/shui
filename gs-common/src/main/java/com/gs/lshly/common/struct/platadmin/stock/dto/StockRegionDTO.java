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
public abstract class StockRegionDTO implements Serializable {

    @Data
    @ApiModel("StockRegionDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "区划编号",hidden = true)
        private String id;

        @ApiModelProperty("父区划编号")
        private String parentId;

        @ApiModelProperty("区划名称")
        private String name;

        @ApiModelProperty("省级名称")
        private String province;

        @ApiModelProperty("市级名称")
        private String city;

        @ApiModelProperty("区级名称")
        private String district;

        @ApiModelProperty("层级")
        private Integer level;
    }

    @Data
    @ApiModel("StockRegionDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "区划编号")
        private String id;
    }


}
