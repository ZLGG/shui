package com.gs.lshly.common.struct.platadmin.foundation.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lxus
 * @since 2020/09/14
 */

public abstract class DataNoticeVO implements Serializable {

    @Data
    @ApiModel("DataNoticeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO  implements Serializable {

        @ApiModelProperty("通知id")
        private String id;

        @ApiModelProperty("通知标题")
        private String title;

        @ApiModelProperty("通知类型ID")
        private String noticeTypeId;

        @ApiModelProperty("通知类型")
        private String noticeTypeName;

        @ApiModelProperty("接收者范围类型[10=全部 20=接受者ID数组]")
        private Integer scopeType;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        @JsonIgnore
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("修改时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;
    }

    @Data
    @ApiModel("DataNoticeVO.DetailVO")
    public static class DetailVO implements Serializable{
        @ApiModelProperty("通知id")
        private String id;

        @ApiModelProperty("通知标题")
        private String title;

        @ApiModelProperty("通知内容")
        private String content;

        @ApiModelProperty("通知类型ID")
        private String noticeTypeId;

        @ApiModelProperty("通知名称")
        private String noticeTypeName;

        @ApiModelProperty("接收者范围类型[10=全部 20=接受者ID数组]")
        private Integer scopeType;

        @ApiModelProperty("所有的通知类型数组")
        private List<DataNoticeTypeVO.DetailVO> noticeTypeList;

    }
}
