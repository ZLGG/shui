package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * SysUser
 * </p>
 * @author lxus
 * @since 2020-09-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_sys_user")
public class SysUser extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 管理平台用户
     */
    private String id;

    /**
     * 登陆名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 头像
     */
    private String headImg;

    /**
     * 账号类型[10=运营管理20=超级管理]
     */
    private Integer type;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 微信名
     */
    private String wxname;

    /**
     * 微信头像
     */
    private String wxheadimg;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;

}
