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
public abstract class PCMerchGoodsAttributeInfoVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsAttributeInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("商品id")
        private String goodId;


        @ApiModelProperty("商品属性名称")
        private String attributeName;


        @ApiModelProperty("商品属性值名称")
        private String attributeValue;


        @ApiModelProperty("排序")
        private Integer idx;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("PCMerchGoodsAttributeInfoVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
