package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-10-22
*/
public abstract class PCMerchPicturesDTO implements Serializable {

    @Data
    @ApiModel("PCMerchPicturesDTO.ETO")
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
    @ApiModel("PCMerchPicturesDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "图库id")
        private String id;
    }


    @Data
    @ApiModel("PCMerchPicturesDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "图库id列表")
        private List<String> idList;
    }

    @Data
    @ApiModel("PCMerchPicturesDTO.MoveGroupETO")
    public static class MoveGroupETO extends BaseDTO {

        @ApiModelProperty(value = "图库id")
        private String id;

        @ApiModelProperty(value = "分组id")
        private String groupId;
    }

}
