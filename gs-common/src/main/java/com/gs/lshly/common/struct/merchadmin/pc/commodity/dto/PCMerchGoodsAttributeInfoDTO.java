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
public abstract class PCMerchGoodsAttributeInfoDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsAttributeInfoDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private String goodId;

        @ApiModelProperty("商品属性名称")
        private String attributeName;

        @ApiModelProperty("商品属性值名称")
        private String attributeValue;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("PCMerchGoodsAttributeInfoDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchGoodsAttributeInfoDTO.GoodIdDTO")
    @AllArgsConstructor
    public static class GoodIdDTO extends BaseDTO {

        @ApiModelProperty("商品id")
        private String goodId;
    }

}
