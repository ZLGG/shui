package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-21
*/
public abstract class PicturesSettingDTO implements Serializable {

    @Data
    @ApiModel("PicturesSettingDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty
        private String id;

        @ApiModelProperty("默认图片路径")
        private String imageUrl;

        @ApiModelProperty("默认图片名称")
        private String imageName;

        @ApiModelProperty("存储引擎")
        private String storageEnginee;

        @ApiModelProperty("图片高度")
        private Integer imgHeight;

        @ApiModelProperty("图片宽度")
        private Integer imgWeight;

        @ApiModelProperty("图片格式")
        private String imageType;

        @ApiModelProperty("图片尺寸类型")
        private Integer imageSizeType;

    }

    @Data
    @ApiModel("PicturesSettingDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String id;
    }


}
