package com.gs.lshly.common.struct.bbb.pc.user.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbbUserQTO implements Serializable {

    @Data
    @ApiModel("BbbUserQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }


    @Data
    @ApiModel("BbbUserQTO.PhoneCheckCodeQTO")
    @Accessors(chain = true)
    public static class PhoneCheckCodeQTO extends BaseDTO {

        @ApiModelProperty("手机号")
        private String phone;

    }

    @Data
    @ApiModel("BbbUserQTO.IntegralLogQTO")
    @Accessors(chain = true)
    public static class IntegralLogQTO extends BaseQTO {

    }
}
