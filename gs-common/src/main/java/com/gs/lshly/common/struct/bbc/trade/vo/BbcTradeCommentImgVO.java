package com.gs.lshly.common.struct.bbc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author oy
* @since 2020-11-06
*/
public abstract class BbcTradeCommentImgVO implements Serializable {

    @Data
    @ApiModel("BbcTradeCommentImgVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易评论图片ID")
        private String id;


        @ApiModelProperty("评论ID")
        private String commentId;


        @ApiModelProperty("评论图片")
        private String commentImg;




    }

    @Data
    @ApiModel("BbcTradeCommentImgVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
