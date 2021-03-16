package com.gs.lshly.common.struct.platadmin.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-15
*/
public abstract class GoodsRelationLabelVO implements Serializable {

    @Data
    @ApiModel("GoodsRelationLabelVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("商品id")
        private String goodsId;


        @ApiModelProperty("标签id")
        private String labelId;




    }

    @Data
    @ApiModel("GoodsRelationLabelVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
