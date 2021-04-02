package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author Starry
* @since 2020-10-06
*/
public abstract class PicturesVO implements Serializable {

    @Data
    @ApiModel("PicturesVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("图库id")
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

        @ApiModelProperty("原图片名称")
        private String originalImageName;


        @ApiModelProperty("图片路径")
        private String imageUrl;


        @ApiModelProperty("图片来源（业务位置)")
        private String source;


        @ApiModelProperty("是否隐藏（1隐藏,0不隐藏）")
        private Integer hidden;


        @ApiModelProperty("操作人")
        private String operator;


        @ApiModelProperty("更新时间")
        private LocalDateTime udate;



    }

    @Data
    @ApiModel("PicturesVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
