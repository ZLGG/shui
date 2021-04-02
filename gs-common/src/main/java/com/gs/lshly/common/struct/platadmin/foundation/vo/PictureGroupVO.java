package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-06
*/
public abstract class PictureGroupVO implements Serializable {

    @Data
    @ApiModel("PictureGroupVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("图片分组id")
        private String id;

        @ApiModelProperty("父id")
        private String parentId;

        @ApiModelProperty("级别")
        private Integer level;

        @ApiModelProperty("所属id（店铺id,-1为平台）")
        private String belongId;

        @ApiModelProperty("分组名称")
        private String groupValue;

        @ApiModelProperty("操作人")
        private String operator;

    }

    @Data
    @ApiModel("PictureGroupVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
