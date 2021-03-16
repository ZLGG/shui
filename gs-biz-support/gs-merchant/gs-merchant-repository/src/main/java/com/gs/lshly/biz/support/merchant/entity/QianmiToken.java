package com.gs.lshly.biz.support.merchant.entity;

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
 * 认证token
 * </p>
 *
 * @author lxus
 * @since 2020-10-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("qm_token")
public class QianmiToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * token
     */
    private String id;

    /**
     * 过期时间
     */
    private Long expire;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * 刷新过期时间
     */
    private Long reExpire;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String userNick;

    /**
     * 上级用户id
     */
    private String parentId;

    /**
     * key
     */
    private String appKey;

    /**
     * secret
     */
    private String appSecret;

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
