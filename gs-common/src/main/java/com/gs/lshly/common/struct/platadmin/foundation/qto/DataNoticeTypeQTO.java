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
public abstract class DataNoticeTypeQTO implements Serializable {

    @Data
    @ApiModel("DataNoticeTypeQTO.QTO")
    public static class QTO extends BaseQTO {

        @ApiModelProperty("通知类型名")
        private String noticeTypeName;

    }
}
