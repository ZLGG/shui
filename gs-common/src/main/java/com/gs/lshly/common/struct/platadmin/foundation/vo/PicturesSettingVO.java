package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
* @author Starry
* @since 2020-10-21
*/
public abstract class PicturesSettingVO implements Serializable {

    @Data
    @ApiModel("PicturesSettingVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
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


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("PicturesSettingVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

}
