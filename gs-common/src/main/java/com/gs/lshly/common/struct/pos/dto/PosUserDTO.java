package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

public abstract class PosUserDTO {

    @Data
    @ApiModel("PosUserDTO.ETO")
    public static class ETO extends BaseDTO {

        /**
         * 会员ID
         */
        private String id;

        /**
         * 用户名(必需)
         */
        private String userName;

        /**
         * 密码 {我们平台给值}
         */
        private String userPwd;

        /**
         * 会员状态[10=可用 20=禁用]  {我们平台给值}
         */
        private Integer state;

        /**
         * 昵称
         */
        private String nick;

        /**
         * 头像
         */
        private String headImg;

        /**
         * 会员类型[10=2b 20=2c],{如果没有,我们平台给值}
         */
        private Integer type;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 手机号
         */
        private String phone;

        /**
         * 生日
         */
        private String birthday;

        /**
         * 性别[10=男  20=女]
         */
        private Integer sex;

        /**
         * 真实姓名
         */
        private String realName;

        /**
         * 省代码(必需)
         */
        private String province;

        /**
         * 市代码(必需)
         */
        private String city;

        /**
         * 县代码(必需)
         */
        private String county;

        /**
         * 省名称(必需)
         */
        private String provinceText;

        /**
         * 市名称(必需)
         */
        private String cityText;

        /**
         * 县名称(必需)
         */
        private String countyText;

        /**
         * 详细地址
         */
        private String realAddress;

        /**
         * 注册来源店铺ID
         */
        private String fromShopId;


    }

}
