package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SiteCustomerServiceQTO implements Serializable {

    @Data
    @ApiModel("SiteCustomerServiceQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
