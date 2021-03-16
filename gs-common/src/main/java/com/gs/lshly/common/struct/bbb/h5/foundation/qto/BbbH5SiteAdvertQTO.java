package com.gs.lshly.common.struct.bbb.h5.foundation.qto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class BbbH5SiteAdvertQTO implements Serializable {

    @Data
    @ApiModel("BbbSiteAdvertQTO.SubjectQTO")
    @Accessors(chain = true)
    public static class SubjectQTO extends BaseDTO {

        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;


    }

    @Data
    @ApiModel("BbbSiteAdvertQTO.CategoryQTO")
    @Accessors(chain = true)
    public static class CategoryQTO extends BaseDTO {

    }
}
