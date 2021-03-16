package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Starry
 * @Date 10:39 2020/10/20
 */
public abstract class PCMerchGoodsParamsVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsParamsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("参数组id")
        private String id;


        @ApiModelProperty("类目id")
        private String categoryId;


        @ApiModelProperty("参数组名")
        private String name;


        @ApiModelProperty("操作人")
        private String operator;
    }

    @Data
    @ApiModel("PCMerchGoodsParamsVO.ParamsListVO")
    public static class ParamsListVO extends ListVO {
        @ApiModelProperty(value = "参数列表")
        private List<PCMerchGoodsParamInfoVO.ListVO> listVOS;
    }
}
