package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class DataNoticeRecvVO implements Serializable {

    @Data
    @ApiModel("DataNoticeRecvVO.ListVO")
    public static class ListVO  implements Serializable {

        @ApiModelProperty("公告接收id")
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
    @ApiModel("DataNoticeRecvVO.DetailVO")
    public static class DetailVO extends ListVO{
    }


}
