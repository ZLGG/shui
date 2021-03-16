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
public abstract class SiteCustomerServiceVO implements Serializable {

    @Data
    @ApiModel("SiteCustomerServiceVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("状态/是否启用[10=启用 20=禁用]")
        private Integer state;

        @ApiModelProperty("账号类型")
        private String type;

        @ApiModelProperty("账号")
        private String account;

    }

    @Data
    @ApiModel("SiteCustomerServiceVO.PhoneVO")
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

    @Data
    @ApiModel("SiteCustomerServiceVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
