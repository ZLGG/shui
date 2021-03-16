package com.gs.lshly.common.struct.bbb.pc.commodityfupin.dto;
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
* @since 2020-12-09
*/
public abstract class PCBbbGoodsFupinDTO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsFupinDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "扶贫商品id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("操作人")
        private String operator;
    }

    @Data
    @ApiModel("PCBbbGoodsFupinDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "扶贫商品id")
        private String id;
    }


}
