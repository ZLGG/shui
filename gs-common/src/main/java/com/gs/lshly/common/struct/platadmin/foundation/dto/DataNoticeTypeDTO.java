package com.gs.lshly.common.struct.platadmin.foundation.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class DataNoticeTypeDTO implements Serializable {

    @Data
    @ApiModel("DataNoticeTypeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "通知类型id", hidden = true)
        private String id;

        @ApiModelProperty("通知类型")
        private String noticeTypeName;

    }

    @Data
    @ApiModel("DataNoticeTypeDTO.EditNoticeTypeDTO")
    @Accessors(chain = true)
    public static class EditNoticeTypeDTO extends BaseDTO {

        @ApiModelProperty(value = "通知类型id", hidden = true)
        private String id;

        @ApiModelProperty("通知类型")
        private String noticeTypeName;

    }

    @Data
    @ApiModel("DataNoticeTypeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("通知类型id")
        private String id;
    }
}
