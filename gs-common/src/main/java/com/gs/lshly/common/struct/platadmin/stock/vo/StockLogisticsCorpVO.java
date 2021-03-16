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
* @since 2020-10-23
*/
public abstract class StockLogisticsCorpVO implements Serializable {

    @Data
    @ApiModel("StockLogisticsCorpVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;


        @ApiModelProperty("物流公司代码")
        private String code;


        @ApiModelProperty("物流公司名称")
        private String name;


        @ApiModelProperty("物流公司网站")
        private String www;

        @ApiModelProperty("物流公司全名")
        private String allname;

        @ApiModelProperty("排序")
        private Integer idx;


    }

    @Data
    @ApiModel("StockLogisticsCorpVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
