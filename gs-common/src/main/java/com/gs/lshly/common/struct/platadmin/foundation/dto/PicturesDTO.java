package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-10-06
*/
public abstract class PicturesDTO implements Serializable {

    @Data
    @ApiModel("PicturesDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "图库id",hidden = true)
        private String id;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id(默认为-1:平台）")
        private String merchantId;

        @ApiModelProperty("分组id")
        private String groupId;

        @ApiModelProperty("存储引擎")
        private String storageEngine;

        @ApiModelProperty("文件大小")
        private String size;

        @ApiModelProperty("图片高度")
        private Integer imgHeight;

        @ApiModelProperty("图片宽度")
        private Integer imgWeight;

        @ApiModelProperty("文件格式")
        private String imgType;

        @ApiModelProperty("图片名字")
        private String imageName;

        @ApiModelProperty("图片路径")
        private String imageUrl;

        @ApiModelProperty("图片来源（业务位置)")
        private String source;

        @ApiModelProperty("是否隐藏（1隐藏,0不隐藏）")
        private Integer hidden;

    }

    @Data
    @ApiModel("PicturesDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "图库id")
        private String id;
    }

    @Data
    @ApiModel("PicturesDTO.MoveGroupDTO")
    public static class MoveGroupDTO extends BaseDTO {

        @ApiModelProperty(value = "图库id")
        private String id;

        @ApiModelProperty("分组id")
        private String groupId;
    }

    @Data
    @ApiModel("PicturesDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "图库id数组")
        private List<String > idList;
    }
}
