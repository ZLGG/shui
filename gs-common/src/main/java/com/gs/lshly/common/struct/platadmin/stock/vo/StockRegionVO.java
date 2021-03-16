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
public abstract class StockRegionVO implements Serializable {

    @Data
    @ApiModel("StockRegionVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("区划编号")
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
    @ApiModel("StockRegionVO.ListVO")
    @Accessors(chain = true)
    public static class ListAllVO implements Serializable{

        @ApiModelProperty("区划编号")
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
    @ApiModel("StockRegionVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
