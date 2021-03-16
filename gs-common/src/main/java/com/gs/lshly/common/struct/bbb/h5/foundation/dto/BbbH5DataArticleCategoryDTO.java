package com.gs.lshly.common.struct.bbb.h5.foundation.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbbH5DataArticleCategoryDTO implements Serializable {

    @Data
    @ApiModel("BbcDataArticleCategoryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("父节点")
        private String perentId;

        @ApiModelProperty("是否第一层级[10=是  20=否]")
        private Integer leveone;

        @ApiModelProperty("图标")
        private String icon;
    }

    @Data
    @ApiModel("BbcDataArticleCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
