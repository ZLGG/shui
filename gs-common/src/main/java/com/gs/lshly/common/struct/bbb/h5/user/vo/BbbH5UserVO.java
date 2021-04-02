package com.gs.lshly.common.struct.bbb.h5.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.bbb.h5.trade.vo.BbbH5TradeListVO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcTradeListVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbbH5UserVO implements Serializable {

    @Data
    @ApiModel("BbbH5UserVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("头像")
        private String headImg;

        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("微信名字")
        private String nickName;

        @ApiModelProperty("手机号码")
        private String phone;

        @ApiModelProperty("积分")
        private Integer integral;

        @ApiModelProperty("优惠卷数量")
        private Integer countCard;

        @ApiModelProperty("订单交易状态数组")
        List<BbbH5TradeListVO.stateCountVO> tradeStateList;

        @ApiModelProperty("微信openid")
        private String wxOpenid;

    }

    @Data
    @ApiModel("BbbH5UserVO.ThirdVO")
    public static class ThirdVO implements Serializable {
        @ApiModelProperty("微信名字")
        private String nickName;
    }

    @Data
    @ApiModel("BbbH5UserVO.LoginVO")
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
    @ApiModel("BbbH5UserVO.LogIntegralVO")
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
    @ApiModel("BbbH5UserVO.InnerUserInfoVO")
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

    }
    @Data
    @ApiModel("BbbH5UserVO.UserIntegralVO")
    public static class UserIntegralVO implements Serializable {

        @ApiModelProperty("可用积分")
        private Integer okIntegral = 0;

        @ApiModelProperty("将要过期")
        private Integer jpassIntegral = 0;

    }
    @Data
    @ApiModel("BbbH5UserVO.UserIntegralRecordVO")
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
    @ApiModel("BbbH5UserVO.UserIntegralStatusVO")
    public static class UserIntegralStatusVO implements Serializable {
        @ApiModelProperty("签到状态[10=可签到 20=已签到]")
        private Integer status;
    }


}
