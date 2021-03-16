package com.gs.lshly.biz.support.trade.entity;

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
* 交易评论记录表
* </p>
*
* @author Starry
* @since 2020-11-16
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_comment_record")
public class TradeCommentRecord extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 交易评论记录ID
    */
    private String id;

    /**
    * 评论ID
    */
    private String commentId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 平台账号ID
    */
    private String platformUserId;

    /**
    * 记录类型:平台/店铺
    */
    private Integer recordType;

    /**
    * 当前状态
    */
    private Integer appealState;

    /**
    * 内容
    */
    private String content;

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
