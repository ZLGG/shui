package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 11:12 2020/9/25
 */
public abstract class GoodsSpecDictionaryVO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ListVO implements Serializable{
        @ApiModelProperty(value = "规格id")
        private String id;

        @ApiModelProperty(value = "规格名称")
        private String name;

        @ApiModelProperty(value = "规格类型")
        private Integer type;

        @ApiModelProperty(value = "规格备注")
        private String remark;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    public static class DetailVO extends ListVO{
        @ApiModelProperty(value = "规格值集合")
        private List<GoodsSpecDictionaryItemVO.ListVO> list = new ArrayList();
    }

    @Data
    public static class GetCategoryVO extends ListVO{
        @ApiModelProperty(value = "类目id")
        private String categoryId;
    }
}
