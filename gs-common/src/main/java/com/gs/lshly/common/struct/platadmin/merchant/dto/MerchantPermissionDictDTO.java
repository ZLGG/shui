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
public abstract class MerchantPermissionDictDTO implements Serializable {

    @Data
    @ApiModel("MerchantPermissionDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("组枚举编号")
        private Integer groupCode;

        @ApiModelProperty("组名称")
        private String groupName;

        @ApiModelProperty("权限名")
        private String permissionName;

        @ApiModelProperty("路由地址")
        private String route;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("MerchantPermissionDictDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
