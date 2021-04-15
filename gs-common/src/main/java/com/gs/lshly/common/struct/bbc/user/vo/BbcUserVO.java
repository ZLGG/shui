package com.gs.lshly.common.struct.bbc.user.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbcUserVO implements Serializable {

    @Data
    @ApiModel("BbcUserVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("微信名字")
        private String nickName;

        @ApiModelProperty("积分")
        private Integer integral;

        @ApiModelProperty("优惠卷数量")
        private Integer countCard;

        @ApiModelProperty("订单交易状态数组")
        List<BbcTradeListVO.stateCountVO> tradeStateList;

        @ApiModelProperty("微信openid")
        private String wxOpenid;

        @ApiModelProperty("用户类型(1-普通用户 2-电信用户)")
        private Integer memberType;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser;

        @ApiModelProperty("电信星级")
        private String telecomsLevel;

        @ApiModelProperty("电信积分")
        private Integer telecomsIntegral;

        @ApiModelProperty("年底过期积分（电信）")
        private Integer telecomsPass;

        @ApiModelProperty("定向积分")
        private Integer directionIntegral;
        
    }

    @Data
    @ApiModel("BbcUserVO.UserIntegralVO")
    public static class UserIntegralVO implements Serializable {

        @ApiModelProperty("可用积分")
        private Integer okIntegral = 0;

        @ApiModelProperty("将要过期")
        private Integer jpassIntegral = 0;

    }

    @Data
    @ApiModel("BbcUserVO.LoginVO")
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
    @ApiModel("BbcUserVO.ThirdVO")
    public static class ThirdVO implements Serializable {
        @ApiModelProperty("微信名字")
        private String nickName;
    }



    @Data
    @ApiModel("BbcUserVO.LogIntegralVO")
    public static class LogIntegralVO implements Serializable {

        @ApiModelProperty("事件类型名")
        private String eventTypeName;

        @ApiModelProperty("事件名")
        private String eventName;

        @ApiModelProperty("事件ID")
        private String eventID;

        @ApiModelProperty("积分数量")
        private String quantity;

        @ApiModelProperty("时间")
        private String eventTime;

    }



    @Data
    @ApiModel("BbcUserVO.InnerUserInfoVO")
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
        
        @ApiModelProperty("所在区域")
        private String region;

        @ApiModelProperty("是否为in会员(1-是 0-否)")
        private Integer isInUser;

    }
    @Data
    @ApiModel("BbcUserVO.UserIntegralRecordVO")
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
    @ApiModel("BbcUserVO.MyIntegralVO")
    public static class MyIntegralVO implements Serializable {

        @ApiModelProperty("可用积分")
        private Integer okIntegral = 0;

        @ApiModelProperty("定向积分")
        private Integer directionIntegral = 0;

        @ApiModelProperty("电话号码")
        private String phone;

    }


}
