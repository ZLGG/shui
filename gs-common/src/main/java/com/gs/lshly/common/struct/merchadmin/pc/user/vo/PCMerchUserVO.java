package com.gs.lshly.common.struct.merchadmin.pc.user.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.common.LegalDictVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-20
*/
public abstract class PCMerchUserVO implements Serializable {

    @Data
    @ApiModel("PCMerchUserVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员用户名")
        private String userName;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("生日")
        private String birthday;

        @ApiModelProperty("私域会员类型ID")
        private String userTypeId;

        @ApiModelProperty("私域会员类型名称")
        private String userTypeName;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("商家私域会员管理状态[10=关联 20=解除]")
        private Integer bindState;

        @ApiModelProperty("企业字典信息")
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty(value = "法人单位ID",hidden = true)
        private String legalId;

        @ApiModelProperty("性别 10=男 20=女")
        private Integer sex;

        @ApiModelProperty("注册时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty("所属店铺名称")
        private String shopName;

    }

    @Data
    @ApiModel("PCMerchUserVO.PrivateUserDetailVO")
    public static class PrivateUserDetailVO implements Serializable{

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员姓名")
        private String userName;

        @ApiModelProperty("会员生日")
        private String birthday;

        @ApiModelProperty("会员注册日期")
        private LocalDateTime cdate;

        @ApiModelProperty("手机号")
        private String phone;

        @ApiModelProperty("姓别")
        private Integer sex;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("私域会员类型ID")
        private String userTypeId;

        @ApiModelProperty("私域会员类型名称")
        private String userTypeName;

        @ApiModelProperty("折扣率")
        private BigDecimal ratio;

        @ApiModelProperty("启用禁用状态[10=启用 20=禁用用]")
        private Integer bindState;

        @ApiModelProperty(value = "法人单位ID",hidden = true)
        private String legalId;

        @ApiModelProperty("企业字典公司信息")
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty("企业字典银行信息")
        private LegalDictVO.BankVO bankVO;

        @ApiModelProperty("企业字典已上传证照")
        private List<LegalDictVO.CertVO> certList;

    }


    @Data
    @ApiModel("PCMerchUserVO.ApplyListVO")
    @Accessors(chain = true)
    public static class ApplyListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员用户名")
        private String userName;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("商家私域会员管理状态[10=关联 20=解除]")
        private Integer bindState;

        @ApiModelProperty("企业字典信息")
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty(value = "法人单位ID",hidden = true)
        private String legalId;

    }

    @Data
    @ApiModel("PCMerchUserVO.ApplyPrivateUserDetailVO")
    public static class ApplyPrivateUserDetailVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员用户名")
        private String userName;

        @ApiModelProperty("私域会员类型ID")
        private String userTypeId;

        @ApiModelProperty("私域会员类型名称")
        private String userTypeName;

        @ApiModelProperty("拒绝原因")
        private String rejectWhy;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("商家私域会员管理状态[10=关联 20=解除]")
        private Integer bindState;

        @ApiModelProperty(value = "法人单位ID",hidden = true)
        private String legalId;

        @ApiModelProperty("企业字典公司信息")
        private LegalDictVO.CompanyVO companyVO;

        @ApiModelProperty("企业字典银行信息")
        private LegalDictVO.BankVO bankVO;

        @ApiModelProperty("企业字典已上传证照")
        private List<LegalDictVO.CertVO> certList;



    }

    @Data
    @ApiModel("PCMerchUserVO.ExportVO")
    public static class ExportVO  implements Serializable {

        @ApiModelProperty(value = "会员用户名",position = 1)
        private String userName;

        @ApiModelProperty(value = "邮箱",position = 2)
        private String email;

        @ApiModelProperty(value = "手机号",position = 3)
        private String phone;

        @ApiModelProperty(value = "生日",position = 4)
        private String birthday;

        @ApiModelProperty(value = "私域会员类型名称",position = 5)
        private String userTypeName;

        @ApiModelProperty(value = "审核状态[10=待审 20=通过 30=拒审]",position = 6)
        private Integer state;

        @ApiModelProperty(value = "商家私域会员管理状态[10=关联 20=解除]",position = 7)
        private Integer bindState;

        @ApiModelProperty(value = "法人姓名",position = 8)
        private String personName;

        @ApiModelProperty(value = "企业类型名称",position = 9)
        private String corpTypeName;

        @ApiModelProperty(value = "公司名称",position = 10)
        private String corpName;

        @ApiModelProperty(value = "公司联系人",position = 11)
        private String corpPersal;

        @ApiModelProperty(value = "公司联系人手机号",position = 12)
        private String corpPhone;

        @ApiModelProperty(value = "性别 10=男 20=女",position = 13)
        private Integer sex;

        @ApiModelProperty(value = "注册时间",position = 14)
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "所属店铺名称",position = 15)
        private String shopName;
    }

    @Data
    @ApiModel("PCMerchUserVO.UserSimpleVO")
    public static class UserSimpleVO  implements Serializable {

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("会员用户名")
        private String userName;

    }
}
