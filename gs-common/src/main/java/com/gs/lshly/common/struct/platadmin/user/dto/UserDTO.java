package com.gs.lshly.common.struct.platadmin.user.dto;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* @author xxfc
* @since 2020-10-05
*/
@SuppressWarnings("serial")
public abstract class UserDTO implements Serializable {

    @Data
    @ApiModel("UserDTO.AddETO")
    @Accessors(chain = true)
    public static class AddETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty(value = "密码")
        private String userPwd;

        @ApiModelProperty(value = "法人代表")
        private String personName;

        @ApiModelProperty("公司联系人")
        private String corpPersal;

        @ApiModelProperty("公司联系人手机号")
        private String corpPhone;


        @ApiModelProperty("营业执照")
        private String corpLicenseCert;

    }

    @Data
    @ApiModel("UserDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "会员昵称")
        private String wxname;

        @ApiModelProperty(value = "手机号")
        private String phone;

        @ApiModelProperty(value = "姓别")
        private Integer sex;

        @ApiModelProperty(value = "生日")
        private String birthday;

    }

    @Data
    @ApiModel("UserDTO.PassworldETO")
    @Accessors(chain = true)
    public static class PassworldETO extends BaseDTO {

        @ApiModelProperty(value = "会员ID",hidden = true)
        private String id;

        @ApiModelProperty("新密码")
        private String userPwd;

        @ApiModelProperty("确认密码")
        private String userPwdCfm;

    }

    @Data
    @ApiModel("UserDTO.Integral")
    @Accessors(chain = true)
    public static class IntegralETO extends BaseDTO {

        @ApiModelProperty(value = "会员ID",hidden = true)
        private String id;

        @ApiModelProperty("会员积分[加=正数,减=负数]")
        private Integer integral;

        @ApiModelProperty("积分过期时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;
    }

    @Data
    @ApiModel("UserDTO.Leve")
    @Accessors(chain = true)
    public static class LeveETO extends BaseDTO {

        @ApiModelProperty(value = "会员ID",hidden = true)
        private String id;

        @ApiModelProperty("会员等级ID")
        private String leveId;

        @ApiModelProperty("会员等级名称")
        private String leveName;

    }

    @Data
    @ApiModel("UserDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "会员ID")
        private String id;
    }

    @Data
    @ApiModel("UserDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "会员ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("UserDTO.IdListDTO")
    @Accessors(chain = true)
    public static class SetUserLeveDTO extends BaseDTO {

        @ApiModelProperty(value = "会员ID",hidden = true)
        private String userId;

        @ApiModelProperty(value = "等级ID")
        private String leveId;
    }

    @Data
    @ApiModel("UserDTO.SetLabelDTO")
    @Accessors(chain = true)
    public static class SetLabelDTO extends BaseDTO {

        @ApiModelProperty(value = "会员ID")
        private String userId;

        @ApiModelProperty(value = "标签ID数组")
        private List<String> labelList;

    }

    @Data
    @ApiModel("UserDTO.ExportDTO")
    @Accessors(chain = true)
    public static class ExportDTO extends BaseDTO {

        @ApiModelProperty(value = "导出的会员ID数组")
        private List<String> userIdList;

    }

    @Data
    @ApiModel("UserDTO.InnerETO")
    @Accessors(chain = true)
    public static class InnerETO extends BaseDTO{
        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty(value = "密码")
        private String userPwd;

        @ApiModelProperty(value = "会员类型 10=2b 20=2c")
        private Integer type;

        @ApiModelProperty(value = "法人单位id")
        private String legalId;

        @ApiModelProperty(value = "状态 10=开启 20=禁用",hidden = true)
        private Integer state;
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
    @ApiModel("UserDTO.GetPhoneValidCodeDTO")
    @Accessors(chain = true)
    public static class GetPhoneValidCodeDTO extends BaseDTO{
    	 @ApiModelProperty(value = "手机号码")
         private String phone;
    }

}
