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
* 会员积分
* </p>
*
* @author xxfc
* @since 2020-11-20
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_integral")
public class UserIntegral extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
    * 数量
    */
    private Integer quantity;

    /**
    * 积分来源[10=平台添加 20=订单 30=签到]
    */
    private Integer fromType;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 业务ID
    */
    private String fromId;

    /**
    * 到期时间
    */
    private LocalDateTime endDate;

    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
