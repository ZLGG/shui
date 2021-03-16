package com.gs.lshly.common.struct.merchadmin.pc.foundation.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-11-16
*/
public abstract class PCMerchDataNoticeVO implements Serializable {

    @Data
    @ApiModel("PCMerchDataNoticeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("")
        private String id;

        @ApiModelProperty("通知标题")
        private String title;

        @ApiModelProperty("通知类型名称")
        private String noticeTypeName;

        @ApiModelProperty("通知时间")

        private LocalDateTime cdate;

        @ApiModelProperty(value = "已读状态[10=未读 20=已读]",example = "2020-11-16 00:00:00")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private Integer state;

    }

    @Data
    @ApiModel("PCMerchDataNoticeVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty("通知内容")
        private String content;

    }

    @Data
    @ApiModel("PCMerchDataNoticeVO.NoticeTypeListVO")
    @Accessors(chain = true)
    public static class NoticeTypeListVO implements Serializable{

        @ApiModelProperty("通知类型ID")
        private String id;

        @ApiModelProperty("通知类型名称")
        private String noticeTypeName;


    }
}
