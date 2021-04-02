package com.gs.lshly.common.struct.common;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class LegalDictDTO implements Serializable {

    @Data
    @ApiModel("LegalDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "法人类型[10=个人 20=企业]",hidden = true)
        private Integer legalType;

        @ApiModelProperty(value = "商业类型[10=买家 20=卖家 30=全部]",hidden = true)
        private Integer businessType;

        @ApiModelProperty(value = "企业类型ID")
        private String corpTypeId;

        @ApiModelProperty(value = "企业类型名称")
        private String corpTypeName;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("营业执照注册号")
        private String corpLicenseNum;

        @ApiModelProperty("营业执照有效期")
        private String corpLicenseIndate;

        @ApiModelProperty(value = "营业执照有效期截止日期")
        private String corpLicenseEndDate;

        @ApiModelProperty("营业执照证件")
        private String corpLicenseCert;

        @ApiModelProperty("公司成立日期")
        private String corpEstablishDate;

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

        @ApiModelProperty("公司邮箱")
        private String corpEmail;

        @ApiModelProperty("注册资本")
        private String corpCapital;

        @ApiModelProperty("公司官网")
        private String corpSite;

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

        @ApiModelProperty("组织机构代码")
        private String tissueCode;

        @ApiModelProperty("组织机构代码证复印件")
        private String tissueCodeCert;

        @ApiModelProperty("税务登记号")
        private String taxCode;

        @ApiModelProperty("税务登记证复印件")
        private String taxCodeCert;

        @ApiModelProperty("银行开户公司名")
        private String bankCorpName;

        @ApiModelProperty("银行开户帐号")
        private String bankAccount;

        @ApiModelProperty("开户行所在地")
        private String bankCityAdress;

        @ApiModelProperty("开户银行")
        private String bankName;

        @ApiModelProperty(value = "证照数组",position = 11)
        private List<CertDTO> certList;

        @ApiModelProperty(value = "入驻资料修改申请id",hidden = true)
        private String editSettledApplyId;

        @ApiModelProperty(value = "10 =待审 20=通过 30 =拒绝 入驻资料修改状态",hidden = true)
        private Integer editSettledState;
    }

    @Data
    @ApiModel("LegalDictDTO.SettledInfoETO")
    @Accessors(chain = true)
    public static class SettledInfoETO extends ETO {
        @ApiModelProperty("店主姓名")
        private String shopManName;

        @ApiModelProperty("店主身份证复印件(正)")
        private String shopManIdcardFront;

        @ApiModelProperty("店主身份证复印件(反)")
        private String shopManIdcardBack;

        @ApiModelProperty("店主手机号")
        private String shopManPhone;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺logo")
        private String shopLogo;

        @ApiModelProperty("详细地址")
        private String shopAddress;

        @ApiModelProperty("电子邮箱")
        private String shopManEmail;

        @ApiModelProperty("店铺类型")
        private Integer shopType;

        @ApiModelProperty("经营品牌")
        private String brandName;
    }

    @Data
    @ApiModel("LegalDictDTO.CompanyDTO")
    public static class CompanyDTO implements Serializable{

        @ApiModelProperty(value = "法人类型[10=个人 20=企业]",hidden = true,position = 1)
        private Integer legalType;

        @ApiModelProperty(value = "商业类型[10=买家 20=卖家 30=全部]",hidden = true,position = 2)
        private Integer businessType;

        @ApiModelProperty(value = "企业类型ID",position = 3)
        private String corpTypeId;

        @ApiModelProperty(value = "企业类型名称",position = 4)
        private String corpTypeName;

        @ApiModelProperty(value = "公司名称",position = 5)
        private String corpName;

        @ApiModelProperty(value = "营业执照注册号",position = 6)
        private String corpLicenseNum;

        @ApiModelProperty(value = "营业执照有效期开始日期",position = 7)
        private String corpLicenseIndate;

        @ApiModelProperty(value = "营业执照有效期截止日期",position = 8)
        private String corpLicenseEndDate;

        @ApiModelProperty(value = "营业执照证件",position = 9)
        private String corpLicenseCert;

        @ApiModelProperty(value = "公司成立日期",position = 10)
        private String corpEstablishDate;

        @ApiModelProperty(value = "法定经营范围",position = 11)
        private String corpLicenseScope;

        @ApiModelProperty(value = "公司所在地址（省市区）",position = 12)
        private String corpCityAddress;

        @ApiModelProperty(value = "公司详细地址",position = 13)
        private String corpRealAddress;

        @ApiModelProperty(value = "公司电话",position = 14)
        private String corpTelephone;

        @ApiModelProperty(value = "公司联系人",position = 15)
        private String corpPersal;

        @ApiModelProperty(value = "公司联系人手机号",position = 14)
        private String corpPhone;

        @ApiModelProperty(value = "公司邮箱",position = 15)
        private String corpEmail;

        @ApiModelProperty(value = "注册资本",position = 16)
        private String corpCapital;

        @ApiModelProperty(value = "公司官网",position = 17)
        private String corpSite;

        @ApiModelProperty(value = "法人姓名",position = 18)
        private String personName;

        @ApiModelProperty(value = "法人身份类型[10=中国大陆 20=非中国大陆]",position = 19)
        private Integer personIdcardType;

        @ApiModelProperty(value = "法人身份证号或护照号",position = 20)
        private String personIdcardNo;

        @ApiModelProperty(value = "法人身份证正面",position = 21)
        private String personIdcardFront;

        @ApiModelProperty(value = "法人身份证反面",position = 22)
        private String personIdcardBack;

        @ApiModelProperty(value = "组织机构代码",position = 23)
        private String tissueCode;

        @ApiModelProperty(value = "组织机构代码证复印件",position = 24)
        private String tissueCodeCert;

        @ApiModelProperty(value = "税务登记号",position = 25)
        private String taxCode;

        @ApiModelProperty(value = "税务登记证复印件",position = 26)
        private String taxCodeCert;

    }


    @Data
    @ApiModel("LegalDictDTO.BankDTO")
    public static class BankDTO implements Serializable{

        @ApiModelProperty(value = "银行开户公司名",position = 1)
        private String bankCorpName;

        @ApiModelProperty(value = "银行开户帐号",position = 2)
        private String bankAccount;

        @ApiModelProperty(value = "开户行所在地",position = 3)
        private String bankCityAdress;

        @ApiModelProperty(value = "开户银行",position = 4)
        private String bankName;
    }

    @Data
    @ApiModel("LegalDictDTO.CertDTO")
    public static class CertDTO implements Serializable{

        @ApiModelProperty(value = "证照ID",position = 1)
        private String id;

        @ApiModelProperty(value = "证照名称",position = 2)
        private String certName;

        @ApiModelProperty(value = "证照文件地址",position = 3)
        private String certUrl;
    }


    @Data
    @ApiModel("LegalDictDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("LegalDictDTO.MerchantApplyIdDTO")
    @AllArgsConstructor
    public static class MerchantApplyIdDTO extends BaseDTO {

        @ApiModelProperty(value = "申请id")
        private String merchantApplyId;
    }


    @Data
    @ApiModel("LegalDictDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private List<String> idList;
    }



    @Data
    @ApiModel("LegalDictDTO.NalETO")
    @Accessors(chain = true)
    public static class NalETO extends BaseDTO {

        @ApiModelProperty(value = "id",position = 1,hidden = true)
        private String id;

        @ApiModelProperty(value = "商业类型[10=买家 20=卖家 30=全部]",position = 2,hidden = true)
        private Integer businessType;

        @ApiModelProperty(value = "姓名",position = 3)
        private String personName;

        @ApiModelProperty(value = "手机号",position = 4)
        private String corpPhone;

        @ApiModelProperty(value = "身份证",position = 5)
        private String personIdcardNo;

        @ApiModelProperty(value = "地区",position = 6)
        private String corpCityAddress;

        @ApiModelProperty(value = "详细地址",position = 7)
        private String corpRealAddress;

        @ApiModelProperty(value = "邮箱",position = 8)
        private String corpEmail;

    }


    @Data
    @ApiModel("LegalDictDTO.LicenseNumDTO")
    public static class LicenseNumDTO extends BaseDTO {

        @ApiModelProperty(value = "营业执照号",hidden = true)
        private String corpLicenseNum;

    }


    @Data
    @ApiModel("LegalDictDTO.InnerETO")
    @Accessors(chain = true)
    public static class InnerETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "法人类型[10=个人 20=企业]",hidden = true)
        private Integer legalType;

        @ApiModelProperty(value = "商业类型[10=买家 20=卖家 30=全部]",hidden = true)
        private Integer businessType;

        @ApiModelProperty(value = "企业类型ID",hidden = true)
        private String corpTypeId;

        @ApiModelProperty(value = "企业类型名称",hidden = true)
        private String corpTypeName;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("营业执照注册号")
        private String corpLicenseNum;

        @ApiModelProperty("营业执照有效期")
        private String corpLicenseIndate;

        @ApiModelProperty("营业执照证件")
        private String corpLicenseCert;

        @ApiModelProperty("公司成立日期")
        private String corpEstablishDate;

        @ApiModelProperty("法定经营范围")
        private String corpLicenseScope;

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

        @ApiModelProperty("县名称")
        private String corpCountyAddress;

        @ApiModelProperty("公司详细地址")
        private String corpRealAddress;

        @ApiModelProperty("公司电话")
        private String corpTelephone;

        @ApiModelProperty("公司联系人")
        private String corpPersal;

        @ApiModelProperty("公司联系人手机号")
        private String corpPhone;

        @ApiModelProperty("公司邮箱")
        private String corpEmail;

        @ApiModelProperty("注册资本")
        private String corpCapital;

        @ApiModelProperty("公司官网")
        private String corpSite;

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

        @ApiModelProperty("组织机构代码")
        private String tissueCode;

        @ApiModelProperty("组织机构代码证复印件")
        private String tissueCodeCert;

        @ApiModelProperty("税务登记号")
        private String taxCode;

        @ApiModelProperty("税务登记证复印件")
        private String taxCodeCert;

        @ApiModelProperty("银行开户公司名")
        private String bankCorpName;

        @ApiModelProperty("银行开户帐号")
        private String bankAccount;

        @ApiModelProperty("开户行所在地")
        private String bankCityAdress;

        @ApiModelProperty("开户银行")
        private String bankName;

        @ApiModelProperty("统一社会信用代码")
        private String unifiedSocialCreditCode;

        @ApiModelProperty(value = "证照数组",position = 11)
        private List<CertDTO> certList = new ArrayList<>();
    }

}
