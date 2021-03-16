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
 * adasdas
 * </p>
 *
 * @author 
 * @since 2020-09-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_data_message")
public class SysDataMessage extends Model {

    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 接受者类型[10=平台 20=会员 30=店铺]
     */
    private Integer recvType;

    /**
     * 接受者ID
     */
    private String recvId;

    /**
     * 发送时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime sendTime;

    /**
     * 状态[未读=10 已读=20]
     */
    private Integer state;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    /**
     * 删除标记
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;

}
