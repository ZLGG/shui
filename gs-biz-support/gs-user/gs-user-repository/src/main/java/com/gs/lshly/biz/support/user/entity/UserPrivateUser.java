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
 * 私域会员
 * </p>
 *
 * @author xxfc
 * @since 2020-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_private_user")
public class UserPrivateUser extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 会员ID
     */
    private String userId;

    /**
     * 会员等级
     */
    private Integer userLeve;

    /**
     * 会员类型ID(商家私域会员的类型ID)
     */
    private String userTypeId;

    /**
     * 商家私域会员管理状态
     */
    private Integer bindState;

    /**
     * 审核状态[10=待审 20=通过 30=拒审]
     */
    private Integer state;

    /**
     * 拒审原因
     */
    private String rejectWhy;

    /**
     * 商家ID
     */
    private String merchantId;

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
