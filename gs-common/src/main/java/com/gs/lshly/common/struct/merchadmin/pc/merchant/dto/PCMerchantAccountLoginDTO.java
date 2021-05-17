package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author xxfc
* @since 2020-10-23
*/
@SuppressWarnings("serial")
public abstract class PCMerchantAccountLoginDTO implements Serializable {

    /**
     * 跟据手机号码获取验证码
     *
     * 
     * @author yingjun
     * @date 2021年5月13日 下午4:34:43
     */
	@Data
	@EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchantAccountLoginDTO.GetPhoneValidCodeDTO")
    public static class GetPhoneValidCodeDTO extends BaseDTO{
    	 /**
		 * 
		 */
		private static final long serialVersionUID = -5793239898671382116L;
		@ApiModelProperty(value = "手机号码")
         private String phone;
    }
	
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("PCMerchantAccountLoginDTO.LoginDTO")
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
    @ApiModel("PCMerchantAccountLoginDTO.CheckPhoneCodeDTO")
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
