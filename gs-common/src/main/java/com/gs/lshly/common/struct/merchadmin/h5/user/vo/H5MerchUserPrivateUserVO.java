package com.gs.lshly.common.struct.merchadmin.h5.user.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gs.lshly.common.struct.common.LegalDictVO;
import com.gs.lshly.common.utils.BigDecimalSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zst
* @since 2021-01-20
*/
public abstract class H5MerchUserPrivateUserVO implements Serializable {

    @Data
    @ApiModel("H5MerchUserPrivateUserVO.ListVO")
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
    @ApiModel("H5MerchUserPrivateUserVO.DetailVO")
    public static class DetailVO extends ListVO {

    }


    @Data
    @ApiModel("H5MerchUserPrivateUserVO.H5ListVO")
    @Accessors(chain = true)
    public static class H5ListVO implements Serializable{

        @ApiModelProperty("Id")
        private String id;

        @ApiModelProperty("会员类型名")
        private String userTypeName;

        @ApiModelProperty("折扣率")
        @JsonSerialize(using = BigDecimalSerialize.class)
        private BigDecimal ratio;
    }


    @Data
    @ApiModel("H5MerchUserPrivateUserVO.PrivateUserDetailVO")
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
    @ApiModel("H5MerchUserPrivateUserVO.ApplyListVO")
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
    @ApiModel("H5MerchUserPrivateUserVO.ApplyPrivateUserDetailVO")
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
}
