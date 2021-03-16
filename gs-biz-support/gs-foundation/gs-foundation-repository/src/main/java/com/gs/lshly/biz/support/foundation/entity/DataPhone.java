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
* 运营人员手机号
* </p>
*
* @author xxfc
* @since 2021-01-28
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_data_phone")
public class DataPhone extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    private String phone;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
