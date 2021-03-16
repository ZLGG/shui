package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantRoleDictDTO implements Serializable {

    @Data
    @ApiModel("MerchantRoleDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("角色名")
        private String roleName;

        @ApiModelProperty("权限ID数组")
        private List<String> permissionIdList;

        @ApiModelProperty(value = "店铺ID",hidden = true)
        private String shopId;

        @ApiModelProperty(value = "商家ID",hidden = true)
        private String merchantId;
    }

    @Data
    @ApiModel("MerchantRoleDictDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private String id;
    }


}
