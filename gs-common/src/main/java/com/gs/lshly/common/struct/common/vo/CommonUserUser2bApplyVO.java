package com.gs.lshly.common.struct.common.vo;

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
public abstract class CommonUserUser2bApplyVO implements Serializable {

    @Data
    @ApiModel("CommonUserUser2bApplyVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

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

        @ApiModelProperty("企业类型")
        private String corpTypeId;

        @ApiModelProperty("企业类型名称")
        private String corpTypeName;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("税务登记号")
        private String taxCode;

        @ApiModelProperty(value = "证照数组",position = 11)
        private List<CertVO> certList = new ArrayList<>();

        @ApiModelProperty(value = "来源店铺ID",hidden = true)
        private String fromShopId;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

    }

    @Data
    @ApiModel("CommonUserUser2bApplyVO.CertVO")
    public static class CertVO implements Serializable{

        @ApiModelProperty(value = "证照ID",position = 1)
        private String certId;

        @ApiModelProperty(value = "证照名称",position = 2)
        private String certName;

        @ApiModelProperty(value = "证照文件地址",position = 3)
        private String certUrl;
    }

    @Data
    @ApiModel("CommonUserUser2bApplyVO.DetailVO")
    public static class DetailVO  implements Serializable {

        @ApiModelProperty("id")
        private String id;

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

        @ApiModelProperty("企业类型")
        private String corpTypeId;

        @ApiModelProperty("企业类型名称")
        private String corpTypeName;

        @ApiModelProperty("公司名称")
        private String corpName;

        @ApiModelProperty("税务登记号")
        private String taxCode;

        @ApiModelProperty("统一社会信用代码")
        private String unifiedSocialCreditCode;

        @ApiModelProperty(value = "证照数组",position = 11)
        private List<CertVO> certList = new ArrayList<>();

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

        @ApiModelProperty("拒审时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime reovkeTime;

        @ApiModelProperty("审核通过时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime okpassTime;

        @ApiModelProperty("申请时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

    }

    @Data
    @ApiModel("CommonUserUser2bApplyVO.ApplyRecordVO")
    @Accessors(chain = true)
    public static class ApplyRecordVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("法人姓名")
        private String personName;

        @ApiModelProperty("企业邮箱")
        private String corpEmail;

        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;

        @ApiModelProperty("申请时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("拒审原因")
        private String revokeWhy;

        @ApiModelProperty("拒审时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime reovkeTime;

        @ApiModelProperty("审核通过时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime okpassTime;

    }
}
