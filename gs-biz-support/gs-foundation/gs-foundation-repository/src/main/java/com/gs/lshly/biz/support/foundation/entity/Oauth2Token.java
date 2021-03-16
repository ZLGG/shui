package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 认证token
 * </p>
 *
 * @author lxus
 * @since 2020-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_oauth2_token")
public class Oauth2Token implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * token
     */
    private String id;

    /**
     * 用户jwt
     */
    private String jwt;

    /**
     * 过期时间
     */
    private LocalDateTime expire;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * 刷新过期时间
     */
    private LocalDateTime refreshExpire;

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
     * 删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
