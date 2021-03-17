package com.gs.lshly.common.struct.bbb.pc.foundation.qto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbSiteTopicQTO implements Serializable {

    @Data
    @ApiModel("BbbSiteTopicQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;
        
        
        @ApiModelProperty(value="终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;

    }
}
