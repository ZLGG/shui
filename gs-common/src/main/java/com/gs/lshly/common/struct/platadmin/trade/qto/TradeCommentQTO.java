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
public abstract class TradeCommentQTO implements Serializable {

    @Data
    @ApiModel("TradeCommentQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }

    @Data
    @ApiModel("TradeCommentQTO.AppealCommentQTO")
    @Accessors(chain = true)
    public static class AppealCommentQTO extends BaseQTO {

        @ApiModelProperty("查询条件(10=全部，20=待处理，30=已处理)")
        private Integer queryCondition;
    }
    @Data
    @ApiModel("TradeCommentQTO.CommentQTO")
    @Accessors(chain = true)
    public static class CommentQTO extends BaseQTO {

        @ApiModelProperty("查询条件(10=全部，20=好评，30=中评，40=差评")
        private Integer queryCondition;

        @ApiModelProperty("订单号")
        private String tradeId;
    }
    @Data
    @ApiModel("TradeCommentQTO.CommentDetailQTO")
    @Accessors(chain = true)
    public static class CommentDetailQTO implements Serializable{

        @ApiModelProperty("评论id")
        private String  id;


    }
}
