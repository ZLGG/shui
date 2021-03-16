package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-23
*/
public abstract class PCMerchMerchantAccountVO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantAccountVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("帐号")
        private String userName;

        @ApiModelProperty("角色名称")
        private String roleName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("店铺id")
        private String shopId;

    }

    @Data
    @ApiModel("PCMerchMerchantAccountVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("角色ID")
        private String roleId;

        @ApiModelProperty("角色名称")
        private String roleName;

        @ApiModelProperty("商家所有的角色数组")
        private List<RoleItemVO> roleList;

    }

    @Data
    @ApiModel("PCMerchMerchantAccountVO.RoleItemVO")
    public static class RoleItemVO implements  Serializable{

        @ApiModelProperty("角色ID")
        private String id;
        /**
         * 角色名称
         */
        private String roleName;
    }

    @Data
    @ApiModel("PCMerchMerchantAccountVO.CheckShopVO")
    @Accessors(chain = true)
    public static class CheckShopVO implements Serializable{

        @ApiModelProperty("店铺ID")
        private String shopId;

    }


}
