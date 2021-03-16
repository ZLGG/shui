package com.gs.lshly.common.struct.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class CommonSiteCustomerServiceVO implements Serializable {

    @Data
    @ApiModel("CommonSiteCustomerServiceVO.ServiceVO")
    @Accessors(chain = true)
    public static class ServiceVO implements Serializable{

        @ApiModelProperty("第三方客服账号类型[平台]")
        private String type;

        @ApiModelProperty("第三方客服账号[平台]")
        private String account;

        @ApiModelProperty("客服热线电话[平台]")
        private String phone;
    }

    @Data
    @ApiModel("CommonSiteCustomerServiceVO.PhoneVO")
    @Accessors(chain = true)
    public static class PhoneVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("热线电话")
        private String phone;

        @ApiModelProperty("热线电话状态/是否启用[10=启用 20=禁用]")
        private Integer phoneState;

        @ApiModelProperty("账号")
        private String account;
    }

}
