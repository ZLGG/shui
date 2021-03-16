package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class SettingsReceiptVO implements Serializable {

    @Data
    @ApiModel("SettingsReceiptVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

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
    @ApiModel("SettingsReceiptVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

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
