package com.gs.lshly.common.struct.merchadmin.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class PCMerchTradeCommentImgDTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCommentImgDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易评论图片ID",hidden = true)
        private String id;

        @ApiModelProperty("评论ID")
        private String commentId;

        @ApiModelProperty("评论记录id")
        private String commentRecordId;

        @ApiModelProperty("评论图片来源（10=初评，20=追评，30=申诉）")
        private Integer commentImgBelong;

        @ApiModelProperty("评论图片")
        private String commentImg;
    }

    @Data
    @ApiModel("PCMerchTradeCommentImgDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易评论图片ID")
        private String id;
    }


}
