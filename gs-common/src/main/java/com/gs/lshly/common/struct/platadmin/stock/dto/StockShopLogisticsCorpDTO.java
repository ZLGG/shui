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
* @since 2020-10-23
*/
public abstract class StockShopLogisticsCorpDTO implements Serializable {

    @Data
    @ApiModel("StockShopLogisticsCorpDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("物流公司总表ID")
        private String logisticsCorpId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("StockShopLogisticsCorpDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;
    }


}
