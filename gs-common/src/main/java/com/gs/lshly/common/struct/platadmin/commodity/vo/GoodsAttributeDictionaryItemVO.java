package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 14:27 2020/9/23
 */
public abstract class GoodsAttributeDictionaryItemVO implements Serializable {

    @Data
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
    public static class DetailVO extends ListVO{

    }

}
