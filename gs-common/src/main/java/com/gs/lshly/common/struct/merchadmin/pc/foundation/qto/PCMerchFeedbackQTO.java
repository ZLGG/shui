package com.gs.lshly.common.struct.merchadmin.pc.foundation.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class PCMerchFeedbackQTO implements Serializable {

    @Data
    @ApiModel("PCMerchFeedbackQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("反馈类型(10-功能故障,20-产品建议,30-其他)")
        private Integer fbType;

    }
}
