package com.gs.lshly.common.struct.fy.fundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-12
*/
public abstract class FyCustomerServiceVO implements Serializable {

    @Data
    @ApiModel("FyCustomerServiceVO.ServiceVO")
    @Accessors(chain = true)
    public static class ServiceVO implements Serializable{

        @ApiModelProperty("第三方客服账号类型[平台]")
        private String type;

        @ApiModelProperty("第三方客服账号[平台]")
        private String account;

        @ApiModelProperty("客服热线电话[平台]")
        private String phone;
    }


}
