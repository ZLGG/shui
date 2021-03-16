package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 11:05 2020/10/20
 */
public abstract class PCMerchGoodsParamInfoVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsParamInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("参数id")
        private String id;


        @ApiModelProperty("参数组id")
        private String paramsId;


        @ApiModelProperty("参数值")
        private String name;


        @ApiModelProperty("参数别名")
        private String alias;


        @ApiModelProperty("操作人")
        private String operator;


    }

    @Data
    @ApiModel("PCMerchGoodsParamInfoVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
