package com.gs.lshly.common.struct.merchadmin.pc.user.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-20
*/
public abstract class PCMerchUserDTO implements Serializable {

    @Data
    @ApiModel("PCMerchUserDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("会员状态[10=可用 20=禁用]")
        private Integer state;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("会员类型[10=2b 20=2c]")
        private Integer type;

        @ApiModelProperty("微信ID")
        private String openid;

        @ApiModelProperty("微信名")
        private String wxname;

        @ApiModelProperty("微信头像")
        private String wxheadimg;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("生日")
        private String birthday;

        @ApiModelProperty("省/市/县")
        private String region;

        @ApiModelProperty("性别[10=男  20=女]")
        private Integer sex;

        @ApiModelProperty("真实姓名")
        private String realName;

        @ApiModelProperty("注册IP")
        private String regIp;

        @ApiModelProperty("法人单位ID")
        private String legalId;

        @ApiModelProperty("会员等级ID")
        private String leveId;

        @ApiModelProperty("会员等级名称")
        private String leveName;

        @ApiModelProperty("注册来源店铺ID")
        private String fromShopId;
    }

    @Data
    @ApiModel("PCMerchUserDTO.IdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchUserDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "会员ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("PCMerchUserDTO.ApplyDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    public static class ApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty(value = "拒审原因")
        private String rejectWhy;

        @ApiModelProperty(value = "私域会员类型ID")
        private String userTypeId;


    }

    @Data
    @ApiModel("PCMerchUserDTO.UserTypeDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserTypeDTO extends BaseDTO {

        @ApiModelProperty(value = "私域ID")
        private String id;

        @ApiModelProperty(value = "会员类型ID")
        private String userTypeId;

    }

    @Data
    @ApiModel("PCMerchUserDTO.TagLabelDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagLabelDTO extends BaseDTO {

        @ApiModelProperty(value = "会员ID数组")
        private List<String> userIdList;

        @ApiModelProperty(value = "标签数组")
        private List<String> labelIdList;

    }


}
