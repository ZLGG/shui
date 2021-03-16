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
public abstract class StockShopLogisticsCorpVO implements Serializable {

    @Data
    @ApiModel("StockShopLogisticsCorpVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;


        @ApiModelProperty("物流公司总表ID")
        private String logisticsCorpId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;




    }

    @Data
    @ApiModel("StockShopLogisticsCorpVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
