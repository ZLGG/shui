package com.gs.lshly.common.struct.bbb.h5.foundation.qto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class BbbH5SiteBroadQTO implements Serializable {

    @Data
    @ApiModel("BbbH5SiteBroadQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }
}
