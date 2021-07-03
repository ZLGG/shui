package com.gs.lshly.biz.support.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 电信会员信息表
 * </p>
 *
 * @author yingjun
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_user_ctcc_in")
public class UserCtccIn extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 开通时间
     */
    private LocalDate startTime;

    /**
     * IN会员到期时间
     */
    private LocalDate endTime;

    /**
     * 当前状态 1：开通中 0：已失效
     */
    private Integer status;

    /**
     * 1:月IN会员  2：年IN会员
     */
    private Integer type;

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
