package com.gs.lshly.common.struct.bbc.user.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author yangxi
 * @create 2021/5/28 10:44
 */
public class BbcSiteNoticeVO implements Serializable {
    @Data
    @ApiModel("BbcSiteNoticeVO.NoticeListVO")
    public static class NoticeListVO implements Serializable {
        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("公告名称")
        private String name;

        @ApiModelProperty("公告内容")
        private String content;

        @ApiModelProperty("公告发布时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;
    }
}
