package com.gs.lshly.biz.support.user.entity;

import java.time.LocalDateTime;
import java.util.Date;

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
 * 电信客户帐户
 * </p>
 *
 * @author yingjun
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_user_ctcc_account")
public class UserCtccAccount extends Model {

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
     * 电信用户ID
     */
    private String ctccId;

    /**
     * 帐户id
     */
    private Long acctId;

    /**
     * 账户名称
     */
    private String acctName;

    /**
     * 合同号
     */
    private String acctCd;

    /**
     * 客户ID
     */
    private Long custId;

    /**
     * 帐户计费类型
     */
    private String acctBillingType;

    /**
     * 代表号码id
     */
    private Long prodInstId;

    /**
     * 生效时间
     */
    private Date effDate;

    /**
     * 失效时间
     */
    private Date expDate;

    /**
     * 集团账户编码
     */
    private String extAcctId;

    /**
     * 集团账户库标识
     */
    private String groupAcctId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String statusCd;

    /**
     * 区县ID
     */
    private Long regionId;

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
