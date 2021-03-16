package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 14:25 2020/9/23
 */
public abstract class GoodsAttributeDictionaryItemDTO implements Serializable {

    @Data
    public static class ETO extends BaseDTO {
        @ApiModelProperty(value = "属性值名称")
        private String attributeValue;
    }

    @Data
    @AllArgsConstructor
    public static class  IdDTO extends BaseDTO{
        @ApiModelProperty(value = "属性值id")
        private String Id;
    }
}
