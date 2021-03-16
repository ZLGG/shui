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
public abstract class DataNoticeRecvDTO implements Serializable {

    @Data
    @ApiModel("DataNoticeRecvDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "公告接收id",hidden = true)
        private String id;

        @ApiModelProperty("公告ID")
        private String noticeId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("状态[10=未读 20=已读]")
        private Integer state;

        @ApiModelProperty("商家ID")
        private String merchantId;

    }

    @Data
    @ApiModel("DataNoticeRecvDTO.EditNoticeRecvDTO")
    @Accessors(chain = true)
    public static class EditNoticeRecvDTO extends BaseDTO {

        @ApiModelProperty(value = "公告接收id",hidden = true)
        private String id;

        @ApiModelProperty("公告ID")
        private String noticeId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("状态[10=未读 20=已读]")
        private Integer state;

        @ApiModelProperty("商家ID")
        private String merchantId;

    }

    @Data
    @ApiModel("DataNoticeRecvDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("公告接收id")
        private String id;
    }

}
