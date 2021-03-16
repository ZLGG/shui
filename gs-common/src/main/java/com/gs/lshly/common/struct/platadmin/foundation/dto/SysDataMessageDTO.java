package com.gs.lshly.common.struct.platadmin.foundation.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class SysDataMessageDTO implements Serializable {

    @Data
    @ApiModel("SysDataMessageDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "站内消息id", hidden = true)
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("接受者类型[10=平台 20=会员 30=店铺]")
        private Integer recvType;

        @ApiModelProperty("接受者ID")
        private String recvId;

        @ApiModelProperty(value = "发送时间", hidden = true)
        private LocalDateTime sendTime;

        @ApiModelProperty("状态[未读=10 已读=20]")
        private Integer state;

    }

    @Data
    @ApiModel("SysDataMessageDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("管理意见反馈id")
        private String id;
    }


}
