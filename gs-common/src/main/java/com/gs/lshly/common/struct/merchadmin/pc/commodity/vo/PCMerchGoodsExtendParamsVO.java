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
public abstract class PCMerchGoodsExtendParamsVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsExtendParamsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;

        @ApiModelProperty("商品id")
        private String shopId;

        @ApiModelProperty("参数组名")
        private String paramName;


        @ApiModelProperty("参数值")
        private String paramValue;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("PCMerchGoodsExtendParamsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
