package com.gs.lshly.common.struct.platadmin.foundation.dto.rbac;

import java.io.Serializable;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.user.dto.UserDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author lxus
 * @since 2020/09/14
 */
@SuppressWarnings("serial")
public abstract class SysUserDTO implements Serializable {

	@EqualsAndHashCode(callSuper=false)
    @Data
    @Accessors(chain = true)
    @ApiModel("平台账号新增编辑对象")
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "管理平台用户id", hidden = true)
        private String id;
        
        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("密码")
        private String pwd;

        @ApiModelProperty("状态")
        private Integer state;

        @ApiModelProperty("账号类型")
        private Integer type;
    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("管理平台用户id")
        private String id;
    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @Accessors(chain = true)
    public static class EditPasswordDTO extends BaseDTO {

        @ApiModelProperty("管理平台用户id")
        private String id;

        @ApiModelProperty("旧密码")
        private String oldPwd;

        @ApiModelProperty("新密码")
        private String newPwd;

        @ApiModelProperty("确认密码")
        private String confirmPwd;
    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("平台账号关联角色参数对象")
    public static class UserRoleETO extends BaseDTO {

        @ApiModelProperty(value = "管理平台用户id" ,hidden = true)
        private String userId;

        @ApiModelProperty("角色id集")
        private List<String> roleIds;
    }
    
    /**
     * 跟据手机号码获取验证码
     *
     * 
     * @author yingjun
     * @date 2021年5月13日 下午4:34:43
     */
	@Data
	@EqualsAndHashCode(callSuper=false)
    @ApiModel("SysUserDTO.GetPhoneValidCodeDTO")
    @Accessors(chain = true)
    public static class GetPhoneValidCodeDTO extends BaseDTO{
    	 @ApiModelProperty(value = "手机号码")
         private String phone;
    }
	
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("SysUserDTO.LoginDTO")
    public static class LoginDTO extends BaseDTO {
        @ApiModelProperty(value = "手机号码")
        private String phone;

        @ApiModelProperty(value = "验证码")
        private String validCode;
    }
	
	/**
     * 跟据手机号码获取验证码
     *
     * http://47.111.147.168:7083/auth/login?
     * username=admin&password=123456&vcId=8c5eb02f-6711-4eea-97c9-26f2ff80fce4&vcode=dYZf
     * @author yingjun
     * @date 2021年5月13日 下午4:34:43
     */
	@Data
	@EqualsAndHashCode(callSuper=false)
    @ApiModel("SysUserDTO.CheckPhoneCodeDTO")
    @Accessors(chain = true)
    public static class CheckDTO extends BaseDTO{
    	 @ApiModelProperty(value = "用户名")
         private String username;
    	 
    	 @ApiModelProperty(value = "密码")
         private String password;
    	 
    	 @ApiModelProperty(value = "vcId")
         private String vcId;
    	 
    	 @ApiModelProperty(value = "vcode")
         private String vcode;
    }
}
