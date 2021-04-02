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
public abstract class PCMerchPictureGroupVO implements Serializable {

    @Data
    @ApiModel("PCMerchPictureGroupVO.ListVO")
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

    }

    @Data
    @ApiModel("PCMerchPictureGroupVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
