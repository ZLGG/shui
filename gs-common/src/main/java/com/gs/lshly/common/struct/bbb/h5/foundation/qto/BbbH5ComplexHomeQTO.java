package com.gs.lshly.common.struct.bbb.h5.foundation.qto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbH5ComplexHomeQTO implements Serializable {

    @Data
    @ApiModel("BbbH5ComplexHomeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }
}
