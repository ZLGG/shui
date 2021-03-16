package com.gs.lshly.common.struct.bbb.pc.user.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-01-30
*/
public abstract class PCBbbUserUser2bApplyLogVO implements Serializable {

    @Data
    @ApiModel("PCBbbUserUser2bApplyLogVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("企业认证id(主表id)")
        private String user2bApplyId;

        @ApiModelProperty("用户名字")
        private String userName;


        @ApiModelProperty("法人类型[10=个人 20=企业]")
        private Integer legalType;


        @ApiModelProperty("商业类型[10=买家 20=卖家 30=买家卖家]")
        private Integer businessType;


        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("企业类型ID")
        private String corpTypeId;


        @ApiModelProperty("企业类型名称")
        private String corpTypeName;


        @ApiModelProperty("公司名称")
        private String corpName;


        @ApiModelProperty("省代码")
        private String corpProvince;


        @ApiModelProperty("市代码")
        private String corpCity;


        @ApiModelProperty("县代码")
        private String corpCounty;


        @ApiModelProperty("公司所在地址（省）")
        private String corpProvinceAddress;


        @ApiModelProperty("公司所在地址（市）")
        private String corpCityAddress;


        @ApiModelProperty("公司所在地址（县区）")
        private String corpCountyAddress;


        @ApiModelProperty("公司详细地址")
        private String corpRealAddress;


        @ApiModelProperty("公司电话")
        private String corpTelephone;


        @ApiModelProperty("企业邮箱")
        private String corpEmail;


        @ApiModelProperty("公司联系人")
        private String corpPersal;


        @ApiModelProperty("公司联系人手机号")
        private String corpPhone;


        @ApiModelProperty("法人姓名")
        private String personName;


        @ApiModelProperty("统一社会信用代码")
        private String unifiedSocialCreditCode;


        @ApiModelProperty("审核状态[10=待审 20=通过 30=拒审]")
        private Integer state;


        @ApiModelProperty("拒审原因")
        private String revokeWhy;


        @ApiModelProperty("拒审时间")
        private LocalDateTime reovkeTime;


        @ApiModelProperty("审核通过时间")
        private LocalDateTime okpassTime;


        @ApiModelProperty("店铺ID")
        private String fromShopId;


        @ApiModelProperty("邀请码")
        private String code;

        @ApiModelProperty("生成时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;


    }

    @Data
    @ApiModel("PCBbbUserUser2bApplyLogVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
