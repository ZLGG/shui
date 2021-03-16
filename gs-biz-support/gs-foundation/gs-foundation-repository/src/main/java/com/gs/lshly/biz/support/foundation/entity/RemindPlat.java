package com.gs.lshly.biz.support.foundation.entity;

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
* 
* </p>
*
* @author xxfc
* @since 2021-02-05
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_remind_plat")
public class RemindPlat extends Model {

    private static final long serialVersionUID = 1L;

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
    private String acceptId;

    /**
    * 触发者ID
    */
    private String triggerId;

    /**
    * 触发业务ID
    */
    private String triggerSid;

    /**
     * 触发者类型[10=会员 20=商家]
     */
    private Integer triggerType;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
