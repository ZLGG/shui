package com.gs.lshly.common.struct.merchadmin.pc.stock.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zzg
* @since 2020-10-22
*/
public abstract class PCMerchStockLogisticsCorpVO implements Serializable {

    @Data
    @ApiModel("PCMerchStockLogisticsCorpVO.ListVO")
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




    }

    @Data
    @ApiModel("PCMerchStockLogisticsCorpVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
