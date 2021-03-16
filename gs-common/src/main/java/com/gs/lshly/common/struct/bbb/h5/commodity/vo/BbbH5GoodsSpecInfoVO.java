package com.gs.lshly.common.struct.bbb.h5.commodity.vo;

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
public abstract class BbbH5GoodsSpecInfoVO implements Serializable {

    @Data
    @ApiModel("BbbH5GoodsSpecInfoVO.ListVO")
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
    @ApiModel("BbbH5GoodsSpecInfoVO.SpecListVO")
    public static class SpecListVO implements Serializable {

        @ApiModelProperty("规格名称")
        private String specName;

        @ApiModelProperty("规格值集合")
        private List<String> specValues;
    }




}
