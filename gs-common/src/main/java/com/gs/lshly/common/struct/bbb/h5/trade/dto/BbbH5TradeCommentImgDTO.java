package com.gs.lshly.common.struct.bbb.h5.trade.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author oy
* @since 2020-11-06
*/
public abstract class BbbH5TradeCommentImgDTO implements Serializable {

    @Data
    @ApiModel("BbcTradeCommentImgDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易评论图片ID",hidden = true)
        private String id;

        @ApiModelProperty("评论ID")
        private String commentId;

        @ApiModelProperty("评论图片")
        private String commentImg;
    }

    @Data
    @ApiModel("BbcTradeCommentImgDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易评论图片ID")
        private String id;
    }


}
