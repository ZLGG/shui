package com.gs.lshly.common.struct.bbb.h5.foundation.qto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbbH5DataArticleCategoryQTO implements Serializable {

    @Data
    @ApiModel("BbcDataArticleCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty("标题")
        private String title;
    }
}
