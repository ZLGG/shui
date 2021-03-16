package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-11-16
*/
public abstract class PCMerchTradeCommentImgVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCommentImgVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易评论图片ID")
        private String id;


        @ApiModelProperty("评论ID")
        private String commentId;


        @ApiModelProperty("评论图片来源（10=初评，20=追评，30=申诉）")
        private Integer commentImgBelong;


        @ApiModelProperty("评论图片")
        private String commentImg;




    }

    @Data
    @ApiModel("PCMerchTradeCommentImgVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
