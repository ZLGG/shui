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

/**
* <p>
* 售后记录表
* </p>
*
* @author oy
* @since 2020-12-06
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_rights_record")
public class TradeRightsRecord extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 售后记录表ID
    */
    private String id;

    /**
    * 售后表ID
    */
    private String rightsId;

    /**
    * 订单ID
    */
    private String tradeId;

    /**
    * 当前状态
    */
    private Integer state;

    /**
    * 退款金额
    */
    private BigDecimal refundAmount;

    /**
    * 售后类型(10:换货,20:仅退款,30:退货退款)
    */
    private Integer rightsType;

    /**
    * 说明
    */
    private String remark;

    /**
    * 用户ID
    */
    private String userId;

    /**
    * 用户名
    */
    private String userName;

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
