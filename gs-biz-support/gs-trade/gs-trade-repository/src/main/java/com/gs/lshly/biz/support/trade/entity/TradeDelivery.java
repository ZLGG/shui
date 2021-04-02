package com.gs.lshly.biz.support.trade.entity;

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
* 订单发货表
* </p>
*
* @author oy
* @since 2020-11-16
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_delivery")
public class TradeDelivery extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 发货表(ID)
    */
    private String id;

    /**
    * 订单ID
    */
    private String tradeId;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 物流公司id
    */
    private String logisticsId;

    /**
    * 物流单号
    */
    private String logisticsNumber;

    /**
    * 操作员id
    */
    private String operatorId;

    /**
    * 操作员姓名
    */
    private String operatorName;

    /**
    * 发货时间
    */
    private LocalDateTime deliveryTime;

    /**
    * 发货备注
    */
    private String deliveryRemark;

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
