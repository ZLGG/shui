package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantAccountQTO implements Serializable {

    @Data
    @ApiModel("MerchantAccountQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("查询条件类型[10=用户名 20=手机号]")
        private Integer conditionType;

        @ApiModelProperty("查询条件值")
        private String conditionValue;

        @ApiModelProperty(value = "终端类型[10=2b,20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("MerchantAccountQTO.PhoneQTO")
    @Accessors(chain = true)
    public static class PhoneQTO extends BaseQTO {

        @ApiModelProperty("帐号")
        private String phone;

    }

}
