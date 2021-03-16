package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
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
* @since 2020-10-22
*/
public abstract class PCMerchPictureGroupDTO implements Serializable {

    @Data
    @ApiModel("PCMerchPictureGroupDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "图片分组id",hidden = true)
        private String id;

        @ApiModelProperty("所属id（店铺id,-1为平台）")
        private String belongId;

        @ApiModelProperty("分组名称")
        private String groupValue;

    }

    @Data
    @ApiModel("PCMerchPictureGroupDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "图片分组id")
        private String id;
    }


}
