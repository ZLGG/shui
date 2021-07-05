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
 * 电信会员信息表
 * </p>
 *
 * @author yingjun
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_user_ctcc")
public class UserCtcc extends Model {

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
     * 客户ID
     */
    private Long custId;

    /**
     * 参与人ID
     */
    private Long partyId;

    /**
     * 纳税人ID
     */
    private Long taxPayerId;

    /**
     * 客户名称
     */
    private String custName;

    /**
     * 客户编码
     */
    private String custNumber;

    /**
     * 客户地址
     */
    private String custAddr;

    /**
     * 地市ID
     */
    private Long regionId;

    /**
     * 客户类型
     */
    private String custType;

    /**
     * 入网时间
     */
    private Date enterDate;

    /**
     * 集团客户编码
     */
    private String extCustId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 证件类型
     */
    private String certType;

    /**
     * 证件号码
     */
    private String certNum;

    /**
     * 集团参与人编码
     */
    private String partyNbr;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 联系人地址
     */
    private String contactAddr;

    /**
     * 联系电话
     */
    private String contactPhone;

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
