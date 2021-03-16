package com.gs.lshly.common.struct.platadmin.trade.dto;
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
* @since 2020-11-17
*/
public abstract class TradeCommentImgDTO implements Serializable {

    @Data
    @ApiModel("TradeCommentImgDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易评论图片ID",hidden = true)
        private String id;

        @ApiModelProperty("评论ID")
        private String commentId;

        @ApiModelProperty("评论图片来源（10=初评，20=追评，30=申诉, 40=再次申诉）")
        private Integer commentImgBelong;

        @ApiModelProperty("评论图片")
        private String commentImg;
    }

    @Data
    @ApiModel("TradeCommentImgDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易评论图片ID")
        private String id;
    }


}
