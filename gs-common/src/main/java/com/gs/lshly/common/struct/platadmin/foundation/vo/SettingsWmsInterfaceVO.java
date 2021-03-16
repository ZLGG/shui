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
public abstract class SettingsWmsInterfaceVO implements Serializable {

    @Data
    @ApiModel("SettingsWmsInterfaceVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


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
    @ApiModel("SettingsWmsInterfaceVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
