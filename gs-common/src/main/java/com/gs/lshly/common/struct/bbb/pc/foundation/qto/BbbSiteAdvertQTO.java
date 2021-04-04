package com.gs.lshly.common.struct.bbb.pc.foundation.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class BbbSiteAdvertQTO implements Serializable {


    @Data
    @ApiModel("BbbSiteAdvertQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]",hidden = true)
        private Integer subject;

    }

}
