package com.gs.lshly.common.struct.bbc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author oy
* @since 2020-12-06
*/
public abstract class BbcTradeRightsImgVO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsImgVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("售后凭证ID")
        private String id;


        @ApiModelProperty("售后表ID")
        private String rightsId;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("凭证图片")
        private String rightsImg;




    }

    @Data
    @ApiModel("BbcTradeRightsImgVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
