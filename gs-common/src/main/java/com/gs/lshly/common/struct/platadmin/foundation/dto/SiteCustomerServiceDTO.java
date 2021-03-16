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
public abstract class SiteCustomerServiceDTO implements Serializable {
    @Data
    @ApiModel("SiteCustomerServiceDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id" )
        private String id;

        @ApiModelProperty("状态/是否启用[10=启用 20=禁用]")
        private Integer state;

        @ApiModelProperty(value = "账号类型")
        private String type;

        @ApiModelProperty("账号")
        private String account;
    }


    @Data
    @ApiModel("SiteCustomerServiceDTO.ETOPhone")
    @Accessors(chain = true)
    public static class ETOPhone extends BaseDTO {

        @ApiModelProperty(value = "id" )
        private String id;

        @ApiModelProperty(value = "热线电话")
        private String phone;

        @ApiModelProperty("热线电话状态/是否启用[10=启用 20=禁用]")
        private Integer phoneState;


    }



    @Data
    @ApiModel("SiteCustomerServiceDTO.UDTO")
    @Accessors(chain = true)
    public static class UDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("状态/是否启用[10=启用 20=禁用]")
        private Integer state;

        @ApiModelProperty(value = "账号类型",hidden = true)
        private String type;

        @ApiModelProperty("账号")
        private String account;
    }


}
