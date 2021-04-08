package com.gs.lshly.common.struct.bbb.pc.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/4/6 16:55
 */

public abstract class BbbMessageVO implements Serializable {

    @Data
    @ApiModel("BbbMessageVO.MessageListVO")
    public static class MessageListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("消息类型 1：系统消息 2活动消息")
        private Integer type;

        @ApiModelProperty("消息明细 11修改密码，12物流通知 21会员消息(购买IN会员)，22返券通知，23活动推送")
        private Integer typeDetail;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("消息内容")
        private String content;

        @ApiModelProperty("用户ID")
        private String userId;

        @ApiModelProperty("0 未读；1：已读 2：已处理(暂无该流程)")
        private Integer status;
    }

    @Data
    @ApiModel("BbbMessageVO.MessageDetailVO")
    public static class MessageDetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("消息类型 1：系统消息 2活动消息")
        private Integer type;

        @ApiModelProperty("消息明细 11修改密码，12物流通知 21会员消息(购买IN会员)，22返券通知，23活动推送")
        private Integer typeDetail;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("消息内容")
        private String content;

        @ApiModelProperty("用户ID")
        private String userId;

        @ApiModelProperty("0 未读；1：已读 2：已处理(暂无该流程)")
        private Integer status;

        @ApiModelProperty("阅读时间")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private Date readDate;
    }
}
