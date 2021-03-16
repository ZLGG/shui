package com.gs.lshly.common.struct.platadmin.user.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class UserUser2bApplyVO implements Serializable {

    @Data
    @ApiModel("UserUser2bApplyVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("营业执照注册号")
        private String corpLicenseNum;

        @ApiModelProperty("法人姓名")
        private String personName;

        @ApiModelProperty("公司成立日期")
        private String corpEstablishDate;

        @ApiModelProperty("公司详细地址")
        private String corpRealAddress;

        @ApiModelProperty("公司经营范围")
        private String CorpLicenseScope;

        @ApiModelProperty("企业类型")
        private String corpTypeId;

        @ApiModelProperty("企业类型名称")
        private String corpTypeName;

        @ApiModelProperty("公司联系人")
        private String corpPersal;

        @ApiModelProperty("公司联系人手机号")
        private String corpPhone;

        @ApiModelProperty("公司所在地址（省）")
        private  String corpProvinceAddress;

        @ApiModelProperty("公司所在地址（市）")
        private String corpCityAddress;

        @ApiModelProperty("公司所在地址（县区）")
        private String corpCountyAddress;

        @ApiModelProperty("企业邮箱")
        private String corpEmail;

        @ApiModelProperty("注册邀请码")
        private String code;

        @ApiModelProperty("公司电话")
        private String corpTelephone;

        @ApiModelProperty("税务登记号")
        private String taxCode;

        @ApiModelProperty("统一社会信用代码")
        private String unifiedSocialCreditCode;


        @ApiModelProperty(value = "证照数组",position = 11)
        private List<CertVO> certList = new ArrayList<>();

        @ApiModelProperty(value = "营业执照")
        private String corpLicenseCert;

        @ApiModelProperty("银行开户公司名")
        private String bankCorpName;

        @ApiModelProperty("银行开户帐号")
        private String bankAccount;

        @ApiModelProperty("开户行所在地")
        private String bankCityAdress;

        @ApiModelProperty("开户银行")
        private String bankName;

        @ApiModelProperty(value = "来源店铺ID",hidden = true)
        private String fromShopId;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒绝原因")
        private String revokeWhy;

    }

    @Data
    @ApiModel("UserUser2bApplyVO.CertVO")
    public static class CertVO implements Serializable{

        @ApiModelProperty(value = "证照ID",position = 1)
        private String certId;

        @ApiModelProperty(value = "证照名称",position = 2)
        private String certName;

        @ApiModelProperty(value = "证照文件地址",position = 3)
        private String certUrl;
    }

    @Data
    @ApiModel("UserUser2bApplyVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

}
