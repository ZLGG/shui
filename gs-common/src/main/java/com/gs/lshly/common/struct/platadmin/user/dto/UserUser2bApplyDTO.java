package com.gs.lshly.common.struct.platadmin.user.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class UserUser2bApplyDTO implements Serializable {

    @Data
    @ApiModel("UserUser2bApplyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("法人类型[10=个人 20=企业]")
        private Integer legalType;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("营业执照注册号")
        private String corpLicenseNum;

        @ApiModelProperty("法人姓名")
        private String personName;

        @ApiModelProperty("法人身份类型[10=中国大陆 20=非中国大陆]")
        private Integer personIdcardType;

        @ApiModelProperty("法人身份证号或护照号")
        private String personIdcardNo;

        @ApiModelProperty("法人身份证正面")
        private String personIdcardFront;

        @ApiModelProperty("法人身份证反面")
        private String personIdcardBack;

        @ApiModelProperty("公司成立日期")
        private LocalDateTime corpEstablishDate;

        @ApiModelProperty("营业执照有效期")
        private LocalDateTime corpLicenseIndate;

        @ApiModelProperty("法定经营范围")
        private String corpLicenseScope;

        @ApiModelProperty("公司所在地址（省市区）")
        private String corpCityAddress;

        @ApiModelProperty("公司详细地址")
        private String corpRealAddress;

        @ApiModelProperty("公司电话")
        private String corpTelephone;

        @ApiModelProperty("公司联系人")
        private String corpPersal;

        @ApiModelProperty("公司联系人手机号")
        private String corpPhone;

        @ApiModelProperty("注册资本")
        private String corpCapital;

        @ApiModelProperty("公司官网")
        private String corpSite;

        @ApiModelProperty("组织机构代码")
        private String tissueCode;

        @ApiModelProperty("组织机构代码证复印件")
        private String tissueCodeImage;

        @ApiModelProperty("税务登记号")
        private String taxCode;

        @ApiModelProperty("税务登记证复印件")
        private String taxCodeImage;

        @ApiModelProperty("银行开户公司名")
        private String bankCorpName;

        @ApiModelProperty("银行开户帐号")
        private String bankAccount;

        @ApiModelProperty("开户行所在地")
        private String bankCityAdress;

        @ApiModelProperty("开户银行")
        private String bankName;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

        @ApiModelProperty("店铺ID")
        private String fromShopId;

        @ApiModelProperty("商家ID")
        private String fromeMerchantId;
    }

    @Data
    @ApiModel("UserUser2bApplyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("UserUser2bApplyDTO.IdListDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private List<String> idList;
    }

    @Data
    @ApiModel("UserUser2bApplyDTO.ApplyDTO")
    @AllArgsConstructor
    public static class ApplyDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

    }
}
