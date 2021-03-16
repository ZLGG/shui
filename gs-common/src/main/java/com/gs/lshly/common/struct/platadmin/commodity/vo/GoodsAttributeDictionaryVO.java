package com.gs.lshly.common.struct.platadmin.commodity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 14:26 2020/9/23
 */
public abstract class GoodsAttributeDictionaryVO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        @ApiModelProperty(value = "属性id")
        private String id;

        @ApiModelProperty(value = "属性名称")
        private String name;

        @ApiModelProperty(value = "属性备注")
        private String remark;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    public static class DetailVO extends ListVO{
        @ApiModelProperty(value = "属性值")
        private List<GoodsAttributeDictionaryItemVO.ListVO> list = new ArrayList<>();
    }

    @Data
    public static class GetCategoryVO extends ListVO{
        @ApiModelProperty(value = "类目id")
        private String categoryId;
    }

}
