package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-09
*/
public abstract class PCMerchGoodsExtendParamsDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsExtendParamsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;

        @ApiModelProperty(value = "商品id")
        private String goodId;

        @ApiModelProperty("参数组名")
        private String paramName;

        @ApiModelProperty("参数值")
        private String paramValue;

    }

    @Data
    @ApiModel("PCMerchGoodsExtendParamsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchGoodsExtendParamsDTO.GoodIdDTO")
    @AllArgsConstructor
    public static class GoodIdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String goodId;
    }


}
