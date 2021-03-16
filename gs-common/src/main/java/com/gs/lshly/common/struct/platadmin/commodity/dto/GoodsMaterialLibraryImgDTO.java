package com.gs.lshly.common.struct.platadmin.commodity.dto;
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
* @since 2020-12-10
*/
public abstract class GoodsMaterialLibraryImgDTO implements Serializable {

    @Data
    @ApiModel("GoodsMaterialLibraryImgDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "商品素材库图片id",hidden = true)
        private String id;

        @ApiModelProperty("素材库id")
        private String materialLibraryId;

        @ApiModelProperty("图片路径")
        private String imageUrl;

        @ApiModelProperty("操作人")
        private String operator;
    }

    @Data
    @ApiModel("GoodsMaterialLibraryImgDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品素材库图片id")
        private String id;
    }


}
