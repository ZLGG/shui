package com.gs.lshly.common.struct.platadmin.foundation.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/09/14
 */
public abstract class DataNoticeRecvQTO implements Serializable {

    @Data
    @ApiModel("DataNoticeRecvQTO.QTO")
    public static class QTO extends BaseQTO {
        @ApiModelProperty("公告接收id")
        private String id;
    }
}
