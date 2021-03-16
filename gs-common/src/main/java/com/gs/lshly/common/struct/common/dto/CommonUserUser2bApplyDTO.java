package com.gs.lshly.common.struct.common.dto;

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
* @since 2020-10-06
*/
public abstract class CommonUserUser2bApplyDTO implements Serializable {

    @Data
    @ApiModel("CommonUserUser2bApplyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("法人姓名")
        private String personName;

        @ApiModelProperty("企业邮箱")
        private String corpEmail;

        @ApiModelProperty("省代码")
        private String corpProvince;

        @ApiModelProperty("市代码")
        private String corpCity;

        @ApiModelProperty("县代码")
        private String corpCounty;

        @ApiModelProperty("省名称")
        private String corpProvinceAddress;

        @ApiModelProperty("市名称")
        private String corpCityAddress;

        @ApiModelProperty("县区名称")
        private String corpCountyAddress;

        @ApiModelProperty("公司详细地址")
        private String corpRealAddress;

        @ApiModelProperty("注册邀请码")
        private String code;

        @ApiModelProperty("公司联系人")
        private String corpPersal;

        @ApiModelProperty("公司联系人手机号")
        private String corpPhone;

        @ApiModelProperty("公司电话")
        private String corpTelephone;

        @ApiModelProperty("企业类型ID")
        private String corpTypeId;

        @ApiModelProperty("企业类型名称")
        private String corpTypeName;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("统一社会信用代码")
        private String unifiedSocialCreditCode;

        @ApiModelProperty(value = "证照数组",position = 11)
        private List<CertDTO> certList;

        @ApiModelProperty(value = "来源店铺ID",hidden = true)
        private String fromShopId;

    }

    @Data
    @ApiModel("CommonUserUser2bApplyDTO.CertDTO")
    public static class CertDTO implements Serializable{

        @ApiModelProperty(value = "证照ID",position = 1)
        private String certId;

        @ApiModelProperty(value = "证照名称",position = 2)
        private String certName;

        @ApiModelProperty(value = "证照文件地址",position = 3)
        private String certUrl;
    }

    @Data
    @ApiModel("CommonUserUser2bApplyDTO.IdDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("CommonUserUser2bApplyDTO.ShareCodeDTO")
    public static class ShareCodeDTO extends BaseDTO {

        @ApiModelProperty(value = "邀请码")
        private String shareCode;
    }

    @Data
    @ApiModel("CommonUserUser2bApplyDTO.CreditCodeDTO")
    public static class CreditCodeDTO extends BaseDTO {
        @ApiModelProperty("统一社会信用代码")
        private String unifiedSocialCreditCode;
    }

}
