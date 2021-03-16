package com.gs.lshly.common.struct.platadmin.trade.qto;
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
* @since 2020-11-17
*/
public abstract class TradeCommentImgQTO implements Serializable {

    @Data
    @ApiModel("TradeCommentImgQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("评论ID")
        private String commentId;

        @ApiModelProperty("评论图片来源（10=初评，20=追评，30=申诉）")
        private Integer commentImgBelong;

    }
}
