package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
* <p>
* 第三方用户登陆表
* </p>
*
* @author lxus
* @since 2020-11-11
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_third_login")
public class UserThirdLogin extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
    * 用户id
    */
    private String userId;

    /**
    * 第三方授权码
    */
    private String accessToken;

    /**
    * access_token过期时间
    */
    private Date expire;

    /**
    * 应用id
    */
    private String appId;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 头像
    */
    private String headImg;

    private Integer sex;

    private String openid;

    private String unionid;

    /**
    * 登陆来源(10-微信公众号,20-微信小程序)
    */
    private Integer loginType;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
