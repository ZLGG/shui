package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsReceiptDTO implements Serializable {

    @Data
    @ApiModel("SettingsReceiptDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("账户名")
        private String name;

        @ApiModelProperty("银行账号")
        private String number;

        @ApiModelProperty("开户银行")
        private String bank;

        @ApiModelProperty("银联号")
        private String unionpay;
    }

    @Data
    @ApiModel("SettingsReceiptDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
