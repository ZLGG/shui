package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;
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
public abstract class PCMerchGoodsMaterialLibraryDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsMaterialLibraryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "商品素材库id",hidden = true)
        private String id;

        @ApiModelProperty("商品类目id")
        private String categoryId;

        @ApiModelProperty("商品品牌id")
        private String brandId;

        @ApiModelProperty("商品标题（商品名称）")
        private String goodsName;

        @ApiModelProperty("商品副标题")
        private String goodsTitle;

        @ApiModelProperty("商品移动端描述")
        private String goodsH5Desc;

        @ApiModelProperty("商品电脑端描述")
        private String goodsPcDesc;

        @ApiModelProperty("操作人")
        private String operator;
    }

    @Data
    @ApiModel("PCMerchGoodsMaterialLibraryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品素材库id")
        private String id;
    }


}
