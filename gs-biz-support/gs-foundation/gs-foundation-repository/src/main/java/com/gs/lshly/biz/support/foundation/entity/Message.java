package com.gs.lshly.biz.support.foundation.entity;

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
 * 
 * </p>
 *
 * @author yingjun
 * @since 2021-03-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_message")
public class Message extends Model  {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 消息类型 1：系统消息 2活动消息
     */
    private Integer type;

    /**
     * 消息明细 11修改密码，12物流通知 21会员消息(购买IN会员)，22返券通知，23活动推送
     */
    private Integer typeDetail;

    private String title;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 0 未读；1：已读 2：已处理(暂无该流程)
     */
    private Integer status;

    /**
     * 阅读时间
     */
    private Date readDate;

    /**
     * 处理时间
     */
    private Date manageDate;

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
