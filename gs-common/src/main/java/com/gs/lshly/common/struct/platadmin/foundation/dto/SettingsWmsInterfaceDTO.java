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
public abstract class SettingsWmsInterfaceDTO implements Serializable {

    @Data
    @ApiModel("SettingsWmsInterfaceDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("对接地址")
        private String dockingAddress;

        @ApiModelProperty("app_key")
        private String appKey;

        @ApiModelProperty("secret")
        private String secret;

        @ApiModelProperty("账户ID")
        private String accountId;
    }

    @Data
    @ApiModel("SettingsWmsInterfaceDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
