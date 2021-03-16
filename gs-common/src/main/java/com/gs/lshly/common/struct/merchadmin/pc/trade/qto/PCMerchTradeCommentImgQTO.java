package com.gs.lshly.common.struct.merchadmin.pc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class PCMerchTradeCommentImgQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCommentImgQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("评论ID")
        private String commentId;

        @ApiModelProperty("评论图片来源（10=初评，20=追评）")
        private Integer commentImgBelong;

        @ApiModelProperty("评论图片")
        private String commentImg;
    }
}
