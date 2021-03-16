package com.gs.lshly.common.struct.platadmin.merchant.vo;
import com.gs.lshly.common.struct.merchadmin.pc.merchant.vo.PCMerchMerchantPermissionDictVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantRoleDictVO implements Serializable {

    @Data
    @ApiModel("MerchantRoleDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("角色名")
        private String roleName;

    }

    @Data
    @ApiModel("MerchantRoleDictVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("角色名")
        private String roleName;

        @ApiModelProperty("当前角色有的权限数组")
        private List<PermissionItemVO> permissionList;

        @ApiModelProperty("系统所有的权限数组")
        private List<PCMerchMerchantPermissionDictVO.ListVO> permissionAllList;
    }

    @Data
    @ApiModel("MerchantRoleDictVO.PermissionItemVO")
    @Accessors(chain = true)
    public static class PermissionItemVO implements Serializable{

        @ApiModelProperty("权限ID")
        private String id;

        @ApiModelProperty("权限组名称")
        private String groupName;

        @ApiModelProperty("权限名称")
        private String permissionName;

    }

}
