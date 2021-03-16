package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 14:25 2020/9/23
 */
public abstract class GoodsAttributeDictionaryDTO implements Serializable {

    @Data
    public static class ETO extends BaseDTO{

        @ApiModelProperty(value = "属性id", hidden = true)
        private String id;

        @ApiModelProperty(value = "属性名称")
        private String name;

        @ApiModelProperty(value = "属性备注")
        private String remark;

        @ApiModelProperty(value = "排序")
        private Integer idx;

        @ApiModelProperty(value = "属性值")
        private List<GoodsAttributeDictionaryItemDTO.ETO> list = new ArrayList<>();

    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO{
        @ApiModelProperty(value = "属性id")
        private String id;
    }

    @Data
    @ApiModel("GoodsAttributeDictionaryDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO{
        @ApiModelProperty(value = "属性id列表")
        private List<String> idList;
    }

}
