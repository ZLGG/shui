package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 14:49 2020/10/20
 */
public abstract class PCMerchGoodsAttributeDictionaryItemVO implements Serializable {
    @Data
    @ApiModel("PCMerchGoodsAttributeDictionaryItemVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{
        @ApiModelProperty(value = "属性值id")
        private String id;

        @ApiModelProperty(value = "属性id")
        private String attributeId;

        @ApiModelProperty(value = "属性值")
        private String attributeValue;
    }

    @Data
    @ApiModel("PCMerchGoodsAttributeDictionaryItemVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

}
