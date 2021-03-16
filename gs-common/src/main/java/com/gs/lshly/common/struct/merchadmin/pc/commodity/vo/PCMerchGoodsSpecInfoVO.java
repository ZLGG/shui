package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-09
*/
public abstract class PCMerchGoodsSpecInfoVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsSpecInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("商品id")
        private String goodId;


        @ApiModelProperty("规格名称")
        private String specName;


        @ApiModelProperty("规格值名称")
        private String specValue;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("PCMerchGoodsSpecInfoVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
