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
 * 平台意见反馈
 * </p>
 *
 * @author 
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_data_feedback")
public class DataFeedback extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 意见反馈id
     */
    private String id;

    /**
     * 反馈类型(10-功能故障,20-产品建议,30-其他)
     */
    private Integer fbType;

    /**
     * 反馈内容
     */
    private String fbContent;

    /**
     * 邮箱
     */
    private String fbEmail;

    /**
     * 联系方式
     */
    private String fbContact;

    /**
     * 提交人id
     */
    private String fbOperatorId;

    /**
     * 提交人姓名
     */
    private String fbOperatorName;

    /**
     * 提交人类型(10-商家,20-会员)
     */
    private Integer fbOperatorType;

    /**
     * 是否处理
     */
    private Integer fbHanderState;

    /**
     * 提交时间
     */
    private LocalDateTime fbOperatorTime;

    /**
     * 处理结果
     */
    private String fbResultContent;

    /**
     * 处理时间
     */
    private LocalDateTime fbResultTime;

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
