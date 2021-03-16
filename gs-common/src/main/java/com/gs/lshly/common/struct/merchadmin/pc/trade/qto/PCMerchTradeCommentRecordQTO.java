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
public abstract class PCMerchTradeCommentRecordQTO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCommentRecordQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

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
}
