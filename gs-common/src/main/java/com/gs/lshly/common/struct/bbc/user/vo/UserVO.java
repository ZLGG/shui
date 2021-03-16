package com.gs.lshly.common.struct.bbc.user.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.platadmin.user.vo.UserLabelDictVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserVO implements Serializable {

    @Data
    @ApiModel("BBC.UserVO.DetailVO")
    public static class DetailVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("用户名")
        private String userName;

        @ApiModelProperty("会员状态[10=可用 20=禁用]")
        private Integer state;

        @ApiModelProperty("会员类型[10=2b 20=2c]")
        private Integer type;


        @ApiModelProperty("微信ID")
        private String openid;


        @ApiModelProperty("微信名")
        private String wxname;


        @ApiModelProperty("微信头像")
        private String wxheadimg;


        @ApiModelProperty("邮箱")
        private String email;


        @ApiModelProperty("手机号")
        private String phone;


        @ApiModelProperty("生日")
        private String birthday;


        @ApiModelProperty("省/市/县(地区)")
        private String region;


        @ApiModelProperty("性别[10=男  20=女]")
        private Integer sex;


        @ApiModelProperty("注册IP")
        private String regIp;

        @ApiModelProperty("注册时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty("会员等级名称")
        private String leveName;


        @ApiModelProperty("注册来源店铺ID")
        private String shopId;

        @ApiModelProperty("注册来源店铺名称")
        private String shopName;

        @ApiModelProperty("会员标签")
        private List<UserLabelDictVO.UserLabelItemVO> userLabelList;
    }


}
