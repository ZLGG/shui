package com.gs.lshly.common.struct.bbb.pc.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.bbb.pc.merchant.vo.PCBbbMerchantUserTypeVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbbUserVO implements Serializable {

    @Data
    @ApiModel("BbbUserVO.EditorUserInfoVO")
    public static class EditorUserInfoVO implements Serializable {

               @ApiModelProperty("昵称")
        private String nick;

        @ApiModelProperty("性别[10=男 20=女 30=保密]")
        private Integer sex;

        @ApiModelProperty("生日")
        private String birthday;

        @ApiModelProperty("姓名")
        private String realName;

        @ApiModelProperty("省代码")
        private String province;

        @ApiModelProperty("市代码")
        private String city;

        @ApiModelProperty("县代码")
        private String county;

        @ApiModelProperty("省名称")
        private String provinceText;

        @ApiModelProperty("市名称")
        private String cityText;

        @ApiModelProperty("县名称")
        private String countyText;

        @ApiModelProperty("详细地址")
        private String realAddress;

    }


    @Data
    @ApiModel("BbbUserVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("邮箱")
        private String email;

        @ApiModelProperty("积分")
        private Integer integral;

        @ApiModelProperty("优惠卷数量")
        private Integer countCard;

        @ApiModelProperty("订单交易状态数组")
        List<BbcTradeListVO.stateCountVO> tradeStateList;

        @ApiModelProperty("微信openid")
        private String wxOpenid;

    }

    @Data
    @ApiModel("BbbUserVO.LoginVO")
    public static class LoginVO extends DetailVO {
        @ApiModelProperty("认证token")
        private String authToken;

        @ApiModelProperty(value = "账号状态", hidden = true)
        private Integer state;

        @ApiModelProperty(value = "账号类型", hidden = true)
        private Integer type;

        @ApiModelProperty(value = "密码", hidden = true)
        public String userPwd;

        @ApiModelProperty(value = "所属密码", hidden = true)
        public String fromShopId;
    }


    @Data
    @ApiModel("BbbUserVO.CheckPasswordVO")
    public static class CheckPasswordVO extends DetailVO {

        @ApiModelProperty("是否正确[0=否 1=是]")
        private Integer checkBool = TrueFalseEnum.否.getCode();

    }


    @Data
    @ApiModel("BbbUserVO.InnerUserInfoVO")
    public static class InnerUserInfoVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("密码")
        private String userPwd;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("会员类型[10=2b 20=2c]")
        private Integer type;

        @ApiModelProperty("会员积分")
        private Integer integral;

        @ApiModelProperty("真实姓名")
        private String realName;

        @ApiModelProperty("电话")
        private String phone;

    }


    @Data
    @ApiModel("BbbUserVO.UserTypeVO")
    public static class UserTypeVO implements Serializable {

        @ApiModelProperty("会员ID")
        private String id;

        @ApiModelProperty("会员类型[10=2b 20=2c]")
        private Integer userType;

    }


    @Data
    @ApiModel("BbbUserVO.PrivateUserInfoVO")
    public static class PrivateUserInfoVO implements Serializable {

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("会员类型[10=2b 20=2c]")
        private Integer userType;

        @ApiModelProperty("店铺私域会员类型折扣信息")
        private PCBbbMerchantUserTypeVO.DetailsVO detailsVO;

    }




    @Data
    @ApiModel("BbbUserVO.UserIntegralVO")
    public static class UserIntegralVO implements Serializable {

        @ApiModelProperty("可用积分")
        private Integer okIntegral = 0;

        @ApiModelProperty("将要过期")
        private Integer jpassIntegral = 0;

    }

    @Data
    @ApiModel("BbbUserVO.UserIntegralRecordVO")
    public static class UserIntegralRecordVO implements Serializable {

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("积分来源[10=平台添加 20=订单]")
        private Integer fromType;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("业务ID(比如：积分来源)")
        private String fromId;

        @ApiModelProperty("到期时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endDate;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

    }
    @Data
    @ApiModel("BbbUserVO.UserIntegralStatusVO")
    public static class UserIntegralStatusVO implements Serializable {
        @ApiModelProperty("签到状态[10=可签到 20=已签到]")
        private Integer status;
    }
    }
