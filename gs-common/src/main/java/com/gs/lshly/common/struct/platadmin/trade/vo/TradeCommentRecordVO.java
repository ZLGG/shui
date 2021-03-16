package com.gs.lshly.common.struct.platadmin.trade.vo;
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
* @since 2020-11-17
*/
public abstract class TradeCommentRecordVO implements Serializable {

    @Data
    @ApiModel("TradeCommentRecordVO.ListVO")
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
    @ApiModel("TradeCommentRecordVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("TradeCommentRecordVO.AppealRecordDetailVO")
    public static class AppealRecordDetailVO implements Serializable {
        @ApiModelProperty("申诉进度信息的申诉状态")
        private Integer appealState;

        @ApiModelProperty("申诉进度信息的最后修改时间")
        private LocalDateTime appealUdate;

        @ApiModelProperty(value = "第一次申诉内容")
        private String firstAppealContent;

        @ApiModelProperty(value = "第一次申诉驳回理由")
        private String firstRefuseIdea;

        @ApiModelProperty(value = "第一次申诉凭证图片")
        private List<TradeCommentImgVO.ListVO> firstAppealImgs = new ArrayList<>();

        @ApiModelProperty(value = "第二次申诉内容")
        private String SecondAppealContent;

        @ApiModelProperty(value = "第一次申诉驳回理由")
        private String SecondRefuseIdea;

        @ApiModelProperty(value = "第二次申诉凭证图片")
        private List<TradeCommentImgVO.ListVO> SecondAppealImgs = new ArrayList<>();


    }

}
