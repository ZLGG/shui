package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
* <p>
* 
* </p>
*
* @author xxfc
* @since 2020-12-08
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_lkl_interaction_log")
public class LklInteractionLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 日志类型10请求20响应
    */
    private Integer logType;

    /**
    * 接口类型10:withdrawalCommit（提现接口）20:？30:？
    */
    private Integer interfaceType;

    /**
    * 请求id,当log_type为20时，必填
    */
    private String requestId;

    /**
    * 数据内容或异常信息
    */
    private String content;

    /**
     * 10:成功 20失败
     */
    private Integer status;

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
    * 逻辑删除标记
    */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
