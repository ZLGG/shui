package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Starry
 * @Date 14:40 2020/10/20
 */
public abstract class PCMerchGoodsAttributeDictionaryVO implements Serializable{

    @Data
    @ApiModel("PCMerchGoodsAttributeDictionaryVO.ListVO")
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
    @ApiModel("PCMerchGoodsAttributeDictionaryVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty(value = "属性值")
        private List<PCMerchGoodsAttributeDictionaryItemVO.ListVO> list;
    }
}
