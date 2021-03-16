package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-10-06
*/
public abstract class PictureGroupDTO implements Serializable {

    @Data
    @ApiModel("PictureGroupDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "图片分组id",hidden = true)
        private String id;

        @ApiModelProperty("分组名称")
        private String groupValue;

        @ApiModelProperty("所属id（店铺id,-1为平台）")
        private String belongId;

    }

    @Data
    @ApiModel("PictureGroupDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "图片分组id")
        private String id;
    }
}
