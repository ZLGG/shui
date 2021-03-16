package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统日志
 * </p>
 *
 * @author lxus
 * @since 2020-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_access_log")
public class AccessLog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 模块
     */
    private String module;

    /**
     * 功能
     */
    private String func;

    /**
     * 类名
     */
    private String className;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求路径
     */
    private String requestUri;

    /**
     * User Agent
     */
    private String userAgent;

    /**
     * 参数
     */
    private String params;

    /**
     * 访问地址
     */
    private String remoteAddr;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 异常信息
     */
    private String exception;

    /**
     * 操作用时（毫秒）
     */
    private Long useTime;

    /**
     * 账户ID
     */
    private String userId;

    /**
     * 账户名称
     */
    private String userName;

    /**
     * 用户类型(10=2B用户;20=2C用户;30=2B商家账号;40=2C商家账号;50=平台运营账号;60=平台超管账号)
     */
    private Integer userType;

    /**
     * 商家id
     */
    private String merchantId;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 微信openid
     */
    private String wxOpenid;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 逻辑删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
