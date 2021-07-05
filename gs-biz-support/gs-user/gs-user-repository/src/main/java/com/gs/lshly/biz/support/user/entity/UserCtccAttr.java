package com.gs.lshly.biz.support.user.entity;

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
 * 电信客户属性
 * </p>
 *
 * @author yingjun
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_user_ctcc_attr")
public class UserCtccAttr extends Model {

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
     * 属性标识
     */
    private Long attrId;

    /**
     * 属性值标识
     */
    private Long attrValueId;

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 备注
     */
    private String remark;

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
