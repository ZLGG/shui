package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-11-16
*/
public abstract class PCMerchTradeCommentRecordVO implements Serializable {

    @Data
    @ApiModel("PCMerchTradeCommentRecordVO.ListVO")
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
    @ApiModel("PCMerchTradeCommentRecordVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("PCMerchTradeCommentRecordVO.AppealContentVO")
    public static class AppealContentVO implements Serializable {
        @ApiModelProperty("申诉内容")
        private String appealContent;

        @ApiModelProperty("申诉时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime appealTime;

        @ApiModelProperty("图片凭证信息列表")
        private List<String> imgVos = new ArrayList<>();
    }
}
