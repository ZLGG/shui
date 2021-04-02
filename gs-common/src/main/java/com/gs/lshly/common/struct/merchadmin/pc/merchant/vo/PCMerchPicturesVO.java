package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-22
*/
public abstract class PCMerchPicturesVO implements Serializable {

    @Data
    @ApiModel("PCMerchPicturesVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("图库id")
        private String id;


        @ApiModelProperty("店铺id")
        private String shopId;


        @ApiModelProperty("商家id(默认为-1:平台）")
        private String merchantId;


        @ApiModelProperty("分组id")
        private Integer groupId;


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




    }

    @Data
    @ApiModel("PCMerchPicturesVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
