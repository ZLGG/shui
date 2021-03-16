package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class DataNoticeTypeVO implements Serializable {

    @Data
    @ApiModel("DataFeedbackVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("通知类型id")
        private String id;

        @ApiModelProperty("通知类型名 ")
        private String noticeTypeName;
    }

    @Data
    @ApiModel("DataNoticeTypeVO.DetailVO")
    public static class DetailVO implements Serializable{

        @ApiModelProperty("通知类型id")
        private String id;

        @ApiModelProperty("通知类型名 ")
        private String noticeTypeName;
    }

}
