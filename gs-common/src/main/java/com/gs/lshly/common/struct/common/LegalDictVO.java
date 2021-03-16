package com.gs.lshly.common.struct.common;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
public abstract class LegalDictVO implements Serializable {

    @Data
    @ApiModel("LegalDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "法人类型[10=个人 20=企业]",position = 2)
        private Integer legalType;

        @ApiModelProperty(value = "商业类型[10=买家 20=卖家 30=全部]",position = 3)
        private Integer businessType;

        @ApiModelProperty(value = "税务登记号",position = 4)
        private String taxCode;

        @ApiModelProperty(value = "营业执照注册号",position = 5)
        private String corpLicenseNum;

        @ApiModelProperty(value = "组织机构代码",position = 6)
        private String tissueCode;

        @ApiModelProperty(value = "法人姓名",position = 7)
        private String personName;

        @ApiModelProperty(value = "公司联系人",position = 8)
        private String corpPersal;

        @ApiModelProperty(value = "公司名称",position = 9)
        private String corpName;

        @ApiModelProperty(value = "公司电话",position = 10)
        private String corpTelephone;

        @ApiModelProperty(value = "企业邮箱",position = 11)
        private String corpEmail;

        @ApiModelProperty(value = "公司联系人手机号",position = 12)
        private String corpPhone;

        @ApiModelProperty(value = "最后修改时间",position = 13)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;

        @ApiModelProperty(value = "企业类型名称",position = 14)
        private String corpTypeName;

    }

    @Data
    @ApiModel("LegalDictVO.DetailVO")
    public static class DetailVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "公司信息",position = 2)
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty(value = "银行信息",position = 3)
        private LegalDictVO.BankVO bankVO;

        @ApiModelProperty(value = "需要证照信息数组",position = 4)
        private List<LegalDictVO.NeedCertVO> needCertListVO = new ArrayList<>();

        @ApiModelProperty(value = "已有证照信息数组",position = 5)
        private List<LegalDictVO.CertVO> certListVO = new ArrayList<>();

        @ApiModelProperty(value = "最后更新时间",position = 6)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

    }

    @Data
    @ApiModel("LegalDictVO.CompanyVO")
    public static class CompanyVO implements Serializable{

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

    }

    @Data
    @ApiModel("LegalDictVO.BankVO")
    public static class BankVO implements Serializable{

        @ApiModelProperty("银行开户公司名")
        private String bankCorpName;

        @ApiModelProperty("银行开户帐号")
        private String bankAccount;

        @ApiModelProperty("开户行所在地")
        private String bankCityAdress;

        @ApiModelProperty("开户银行")
        private String bankName;
    }

    @Data
    @ApiModel("LegalDictVO.NeedCertVO")
    public static class NeedCertVO implements Serializable{

        @ApiModelProperty(value = "证照ID",position = 1)
        private String id;

        @ApiModelProperty(value = "证照名称",position = 2)
        private String certName;

    }

    @Data
    @ApiModel("LegalDictVO.CertVO")
    public static class CertVO implements Serializable{

        @ApiModelProperty(value = "证照ID",position = 1)
        private String id;

        @ApiModelProperty(value = "证照名称",position = 2)
        private String certName;

        @ApiModelProperty(value = "证照文件地址",position = 3)
        private String certUrl;
    }

    @Data
    @ApiModel("LegalDictVO.NalListVO")
    @Accessors(chain = true)
    public static class NalListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "商业类型[10=买家 20=卖家 30=全部]",position = 2)
        private Integer businessType;

        @ApiModelProperty(value = "姓名",position = 3)
        private String personName;

        @ApiModelProperty(value = "手机号",position = 4)
        private String corpPhone;

        @ApiModelProperty(value = "身分证",position = 5)
        private String personIdcardNo;

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

        @ApiModelProperty(value = "详细地址",position = 7)
        private String corpRealAddress;

        @ApiModelProperty(value = "邮箱",position = 8)
        private String corpEmail;

        @ApiModelProperty(value = "最后修改时间",position = 9)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime udate;

    }

    @Data
    @ApiModel("LegalDictVO.NalDetailVO")
    public static class NalDetailVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "商业类型[10=买家 20=卖家 30=全部]",position = 2)
        private Integer businessType;

        @ApiModelProperty(value = "姓名",position = 3)
        private String personName;

        @ApiModelProperty(value = "手机号",position = 4)
        private String corpPhone;

        @ApiModelProperty(value = "身分证",position = 5)
        private String personIdcardNo;

        @ApiModelProperty(value = "地区",position = 6)
        private String corpCityAddress;

        @ApiModelProperty(value = "详细地址",position = 7)
        private String corpRealAddress;

        @ApiModelProperty(value = "邮箱",position = 8)
        private String corpEmail;

    }


    @Data
    @ApiModel("LegalDictVO.CheckCertVO")
    public static class CheckCertVO implements Serializable{

        @ApiModelProperty(value = "必须上传,确未上传的证照数组")
        private List<CertVO> certList = new ArrayList<>();

    }


    @Data
    @ApiModel("LegalDictVO.InnerDetailVO")
    public static class InnerDetailVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "公司信息",position = 2)
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty(value = "银行信息",position = 3)
        private LegalDictVO.BankVO bankVO;

        @ApiModelProperty(value = "已有证照信息数组",position = 5)
        private List<LegalDictVO.CertVO> certListVO = new ArrayList<>();

        @ApiModelProperty(value = "最后更新时间",position = 11)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

    }

}
