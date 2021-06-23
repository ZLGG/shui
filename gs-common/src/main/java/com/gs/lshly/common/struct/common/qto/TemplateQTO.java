package com.gs.lshly.common.struct.common.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

public abstract class TemplateQTO implements Serializable {

    @Data
    @ApiModel("TemplateQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
