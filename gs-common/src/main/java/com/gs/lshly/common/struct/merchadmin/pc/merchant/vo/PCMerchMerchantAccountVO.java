package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
* @author xxfc
* @since 2020-10-23
*/
@SuppressWarnings("serial")
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

        @ApiModelProperty("商户名称")
        private String name;

        @ApiModelProperty("商户类别（10=积分商户 20=普通商户）")
        private Integer type;

        @ApiModelProperty("联系人")
        private String realName;

        @ApiModelProperty("商户属地")
        private String merAddress;
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

        @ApiModelProperty("商户名称")
        private String name;

        @ApiModelProperty("商户类别（10=积分商户 20=普通商户）")
        private Integer type;

        @ApiModelProperty("商户属地（省名称）")
        private String province;

        @ApiModelProperty("商户属地（市名称）")
        private String city;

        @ApiModelProperty("协议到期时间")
        private Date expirationTime;

        @ApiModelProperty("协议号")
        private String agreementCode;

        @ApiModelProperty("供应商纳税性质(10=一般纳税人 20=小规模纳税人)")
        private Integer taxType;

        @ApiModelProperty("税率(%)")
        private Double taxRate;
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

	@Data
    @ApiModel("PCMerchMerchantAccountVO.AccountDetailVO")
    public static class AccountDetailVO implements Serializable {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("姓名")
        private String realName;
        
        /**
         * 密码
         */
        private String userPwd;

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

        @ApiModelProperty("商户名称")
        private String name;

        @ApiModelProperty("商户类别（10=积分商户 20=普通商户）")
        private Integer type;

        @ApiModelProperty("商户属地（省名称）")
        private String province;

        @ApiModelProperty("商户属地（市名称）")
        private String city;

        @ApiModelProperty("协议到期时间")
        private Date expirationTime;

        @ApiModelProperty("协议号")
        private String agreementCode;

        @ApiModelProperty("供应商纳税性质(10=一般纳税人 20=小规模纳税人)")
        private Integer taxType;

        @ApiModelProperty("税率(%)")
        private Double taxRate;
    }

}
