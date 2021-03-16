package com.gs.lshly.biz.support.trade.entity;

import java.math.BigDecimal;
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
* @author zst
* @since 2020-12-10
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_margin_detail")
public class TradeMarginDetail extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 保证金ID
    */
    private String marginId;

    /**
    * 商家ID
    */
    private String shopId;

    /**
    * 店铺名称
    */
    private String shopName;

    /**
    * 用户ID
    */
    private String userId;

    /**
    * 用户姓名
    */
    private String userName;

    /**
    * 交易金额
    */
    private BigDecimal payAmount;

    /**
    * 银行交易流水号
    */
    private String bankSerialNum;

    /**
    * 关联订单编号
    */
    private String tradeCode;

    /**
    * 处罚理由
    */
    private String penaltyReason;

    /**
    * 违规描述
    */
    private String illegalDescription;

    /**
    * 交易类型(10充值 20扣款 30额度调整)
    */
    private Integer payType;

    /**
    * 备注
    */
    private String comment;

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
