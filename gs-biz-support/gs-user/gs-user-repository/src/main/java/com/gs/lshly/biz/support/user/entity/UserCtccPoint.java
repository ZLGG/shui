package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 会员积分余额数据
 * </p>
 *
 * @author yingjun
 * @since 2021-03-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_user_ctcc_point")
public class UserCtccPoint extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 会员ID
     */
    private String userId;

    /**
     * 积分账户标识
     */
    private Long pointAcctId;

    /**
     * 客户标识
     */
    private Long custId;

    /**
     * 积分余额
     */
    private Long pointBalance;

    /**
     * 年末到期积分
     */
    private Long yearBalance;

    /**
     * 积分类型组编码
     */
    private String pointTypeNum;

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
