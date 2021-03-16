package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 11:11 2020/9/25
 */
public abstract class GoodsSpecDictionaryDTO implements Serializable {

    @Data
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "规格id", hidden = true)
        private String id;

        @ApiModelProperty(value = "规格名称")
        private String name;

        @ApiModelProperty(value = "规格类型")
        private Integer type;

        @ApiModelProperty(value = "规格备注")
        private String remark;

        @ApiModelProperty(value = "排序")
        private Integer idx;

        @ApiModelProperty(value = "规格值")
        private List<GoodsSpecDictionaryItemDTO.ETO> list = new ArrayList();
    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO{
        @ApiModelProperty(value = "规格id")
        private String id;
    }

    @Data
    @ApiModel("GoodsSpecDictionaryDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO{
        @ApiModelProperty(value = "规格id")
        private List<String> idList;
    }

}
