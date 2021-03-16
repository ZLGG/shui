package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author xxfc
* @since 2021-01-28
*/
public abstract class DataPhoneQTO implements Serializable {

    @Data
    @ApiModel("DataPhoneQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("手机号")
        private String phone;
    }
}
