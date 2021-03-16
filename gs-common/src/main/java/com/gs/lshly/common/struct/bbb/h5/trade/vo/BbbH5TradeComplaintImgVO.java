package com.gs.lshly.common.struct.bbb.h5.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-12-23
*/
public abstract class BbbH5TradeComplaintImgVO implements Serializable {

    @Data
    @ApiModel("BbbH5TradeComplaintImgVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("图片id")
        private String id;


        @ApiModelProperty("订单投诉id")
        private String complaintId;


        @ApiModelProperty("图片地址")
        private String imgUrl;




    }

    @Data
    @ApiModel("BbbH5TradeComplaintImgVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
