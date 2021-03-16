package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 11:53 2020/9/29
 */
public abstract class CategoryBrandDTO implements Serializable {

    @Data
    public static class ETO extends BaseDTO{
        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;

        @ApiModelProperty(value = "品牌id")
        private String brandId;

        @ApiModelProperty(value = "类别id")
        private String categoryId;
    }
}
