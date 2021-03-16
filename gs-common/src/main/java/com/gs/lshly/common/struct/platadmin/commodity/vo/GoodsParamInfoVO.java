package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-09-29
*/
public abstract class GoodsParamInfoVO implements Serializable {

    @Data
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
    public static class DetailVO extends ListVO {

    }
}
