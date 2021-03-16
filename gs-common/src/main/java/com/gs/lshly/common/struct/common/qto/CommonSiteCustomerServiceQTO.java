package com.gs.lshly.common.struct.common.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class CommonSiteCustomerServiceQTO implements Serializable {

    @Data
    @ApiModel("CommonSiteCustomerServiceQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
