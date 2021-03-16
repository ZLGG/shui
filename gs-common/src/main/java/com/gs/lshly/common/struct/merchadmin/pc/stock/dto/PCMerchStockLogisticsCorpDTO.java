package com.gs.lshly.common.struct.merchadmin.pc.stock.dto;
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
* @since 2020-10-22
*/
public abstract class PCMerchStockLogisticsCorpDTO implements Serializable {

    @Data
    @ApiModel("PCMerchStockLogisticsCorpDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("物流公司代码")
        private String code;

        @ApiModelProperty("物流公司名称")
        private String name;

        @ApiModelProperty("物流公司网站")
        private String www;
    }

    @Data
    @ApiModel("PCMerchStockLogisticsCorpDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;
    }


}
