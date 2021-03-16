package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class SysDataMessageVO implements Serializable {

    @Data
    @ApiModel("SysDataMessageVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {
        @ApiModelProperty("站内消息id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("接受者类型[10=平台 20=会员 30=店铺]")
        private Integer recvType;

        @ApiModelProperty("接受者ID")
        private String recvId;

        @ApiModelProperty("发送时间")
        private LocalDateTime sendTime;

        @ApiModelProperty("状态[未读=10 已读=20]")
        private Integer state;
    }
    @Data
    @ApiModel("SysDataMessageVO.DetailVO")
    public static class DetailVO extends SysDataMessageVO.ListVO {
    }
}
