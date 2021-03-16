package com.gs.lshly.common.struct.merchadmin.pc.stock.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author R
* @since 2020-10-24
*/
public abstract class PCMerchStockShopLogisticsCorpVO implements Serializable {

    @Data
    @ApiModel("PCMerchStockShopLogisticsCorpVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("物流公司ID")
        private String id;

        @ApiModelProperty("物流公司代码")
        private String code;

        @ApiModelProperty("物流公司名称")
        private String name;

        @ApiModelProperty("是否启用[0=否 1=是]")
        private Integer state;

    }

    @Data
    @ApiModel("PCMerchStockShopLogisticsCorpVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
