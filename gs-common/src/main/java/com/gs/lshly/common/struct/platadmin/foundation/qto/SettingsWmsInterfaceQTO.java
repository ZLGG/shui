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
public abstract class SettingsWmsInterfaceQTO implements Serializable {

    @Data
    @ApiModel("SettingsWmsInterfaceQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("对接地址")
        private String dockingAddress;

        @ApiModelProperty("app_key")
        private String appKey;

        @ApiModelProperty("secret")
        private String secret;

        @ApiModelProperty("账户ID")
        private String accountId;
    }
}
