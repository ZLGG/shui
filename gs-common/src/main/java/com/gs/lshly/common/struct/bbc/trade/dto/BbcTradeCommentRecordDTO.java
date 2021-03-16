package com.gs.lshly.common.struct.bbc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author oy
* @since 2020-11-06
*/
public abstract class BbcTradeCommentRecordDTO implements Serializable {

    @Data
    @ApiModel("BbcTradeCommentRecordDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "交易评论记录ID",hidden = true)
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
    @ApiModel("BbcTradeCommentRecordDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "交易评论记录ID")
        private String id;
    }


}
