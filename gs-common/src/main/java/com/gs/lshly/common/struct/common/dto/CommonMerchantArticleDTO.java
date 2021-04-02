package com.gs.lshly.common.struct.common.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 17:50 2021/3/21
 */
public abstract class CommonMerchantArticleDTO implements Serializable {

    @Data
    @ApiModel("CommonMerchantArticleDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

    }
}
