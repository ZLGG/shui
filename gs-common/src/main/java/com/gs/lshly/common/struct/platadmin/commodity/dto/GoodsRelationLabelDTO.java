package com.gs.lshly.common.struct.platadmin.commodity.dto;
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
* @since 2020-10-15
*/
public abstract class GoodsRelationLabelDTO implements Serializable {

    @Data
    @ApiModel("GoodsRelationLabelDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private List<String> goodsId;

        @ApiModelProperty("标签id")
        private List<String> labelId;

    }

    @Data
    @ApiModel("GoodsRelationLabelDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String id;
    }

    @Data
    @ApiModel("GoodsRelationLabelDTO.GoodsIdDTO")
    @AllArgsConstructor
    public static class GoodsIdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String goodsId;
    }

    @Data
    @ApiModel("GoodsRelationLabelDTO.LabelIdDTO")
    @AllArgsConstructor
    public static class LabelIdDTO extends BaseDTO {

        @ApiModelProperty(value = "标签id")
        private String labelId;
    }


}
