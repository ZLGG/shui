package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-27
*/
public abstract class PCMerchGoodsStockDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsStockDTO.SkuAlarmETO")
    @Accessors(chain = true)
    public static class SkuAlarmETO extends BaseDTO {

        @ApiModelProperty(value = "主键skuId",hidden = true)
        private String id;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("库存报警数")
        private Integer stockNum;

    }

}
