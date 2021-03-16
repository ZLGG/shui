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
public abstract class SettingsReceiptQTO implements Serializable {

    @Data
    @ApiModel("SettingsReceiptQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("账户名")
        private String name;

        @ApiModelProperty("银行账号")
        private String number;

        @ApiModelProperty("开户银行")
        private String bank;

        @ApiModelProperty("银联号")
        private String unionpay;
    }
}
