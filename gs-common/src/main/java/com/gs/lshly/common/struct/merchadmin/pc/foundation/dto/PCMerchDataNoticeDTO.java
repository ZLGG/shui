package com.gs.lshly.common.struct.merchadmin.pc.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-11-16
*/
public abstract class PCMerchDataNoticeDTO implements Serializable {

    @Data
    @ApiModel("PCMerchDataNoticeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("通知标题")
        private String title;

        @ApiModelProperty("通知内容")
        private String content;

        @ApiModelProperty("通知类型ID")
        private String noticeTypeId;

        @ApiModelProperty("通知类型名称")
        private String noticeTypeName;

        @ApiModelProperty("接收者范围类型[10=全部 20=接受者ID数组]")
        private Integer scopeType;
    }

    @Data
    @ApiModel("PCMerchDataNoticeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }


}
