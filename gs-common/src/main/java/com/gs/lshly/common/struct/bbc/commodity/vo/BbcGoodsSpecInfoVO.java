package com.gs.lshly.common.struct.bbc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-10-09
*/
@SuppressWarnings("serial")
public abstract class BbcGoodsSpecInfoVO implements Serializable {

    @Data
    @ApiModel("BbcGoodsSpecInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("商品id")
        private String goodId;


        @ApiModelProperty("规格名称")
        private String specName;


        @ApiModelProperty("规格值名称")
        private String specValue;


        @ApiModelProperty("操作人")
        private String operator;

    }

    @Data
    @ApiModel("BbcGoodsSpecInfoVO.SpecListVO")
    public static class SpecListVO implements Serializable {

        @ApiModelProperty("规格名称")
        private String specName;

        @ApiModelProperty("规格值集合")
        private List<String> specValues;
    }


}
