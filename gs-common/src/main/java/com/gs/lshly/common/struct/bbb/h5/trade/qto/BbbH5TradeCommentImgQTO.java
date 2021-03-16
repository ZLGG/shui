package com.gs.lshly.common.struct.bbb.h5.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author oy
* @since 2020-11-06
*/
public abstract class BbbH5TradeCommentImgQTO implements Serializable {

    @Data
    @ApiModel("BbcTradeCommentImgQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("评论ID")
        private String commentId;

        @ApiModelProperty("评论图片")
        private String commentImg;
    }
}
