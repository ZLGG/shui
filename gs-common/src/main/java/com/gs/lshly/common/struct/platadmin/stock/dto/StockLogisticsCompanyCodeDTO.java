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
* @since 2020-10-30
*/
public abstract class StockLogisticsCompanyCodeDTO implements Serializable {

    @Data
    @ApiModel("StockLogisticsCompanyCodeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("物流公司编码")
        private String code;

        @ApiModelProperty("物流公司名称")
        private String logisticsCompanyAme;
    }

    @Data
    @ApiModel("StockLogisticsCompanyCodeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
