package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

/**
* <p>
* 
* </p>
*
* @author xxfc
* @since 2021-02-05
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_remind_user")
public class RemindUser extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 提醒ID
    */
    private String id;

    /**
    * 提醒内容
    */
    private String content;

    /**
    * 提醒业务类型
    */
    private Integer cType;

    /**
    * 提醒方式[10=站内信 20=微信]
    */
    private Integer cStyle;

    /**
    * 接受者ID
    */
    private String accetId;

    /**
    * 触发者ID
    */
    private String triggerId;

    /**
    * 触发者业务ID
    */
    private String triggerSid;

    /**
     * 触发者类型[10=平台 20=商家]
     */
    private Integer triggerType;

    /**
     * 10=待读 20=已读
     */
    private Integer state;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
