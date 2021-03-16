package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantDTO implements Serializable {

    @Data
    @ApiModel("MerchantDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("商家名称")
        private String merchantName;

        @ApiModelProperty("商家头像")
        private String merchantHeadImg;

        @ApiModelProperty("法人单位ID")
        private String legalId;

        @ApiModelProperty("主帐号ID")
        private String mainAccountId;
    }

    @Data
    @ApiModel("MerchantDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
