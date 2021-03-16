package com.gs.lshly.common.struct.bbb.h5.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author oy
* @since 2020-11-06
*/
public abstract class BbbH5TradeCommentRecordVO implements Serializable {

    @Data
    @ApiModel("BbcTradeCommentRecordVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易评论记录ID")
        private String id;


        @ApiModelProperty("评论ID")
        private String commentId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("平台账号ID")
        private String platformUserId;


        @ApiModelProperty("记录类型:平台/店铺")
        private Integer recordType;


        @ApiModelProperty("当前状态")
        private Integer appealState;


        @ApiModelProperty("内容")
        private String content;




    }

    @Data
    @ApiModel("BbcTradeCommentRecordVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
