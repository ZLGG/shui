package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-12-17
*/
public abstract class PCMerchTradeRightsImgVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeRightsImgVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("售后凭证ID")
        private String id;


        @ApiModelProperty("售后表ID")
        private String rightsId;


        @ApiModelProperty("订单ID")
        private String tradeId;


        @ApiModelProperty("评论图片")
        private String rightsImg;




    }

    @Data
    @ApiModel("PCMerchTradeRightsImgVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
