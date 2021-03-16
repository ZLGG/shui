package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-09
*/
public abstract class PCMerchShopServiceDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopServiceDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("状态/是否启用[10=启用 20=禁用]")
        private Integer state;

        @ApiModelProperty("账号")
        private String account;

    }

    @Data
    @ApiModel("PCMerchShopServiceDTO.ETOPhone")
    @Accessors(chain = true)
    public static class ETOPhone extends BaseDTO {

        @ApiModelProperty(value = "热线电话")
        private String phone;

        @ApiModelProperty("热线电话状态/是否启用[10=启用 20=禁用]")
        private Integer phoneState;

    }

    @Data
    @ApiModel("PCMerchShopServiceDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
