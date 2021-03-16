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
* @since 2020-12-09
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_margin")
public class TradeMargin extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
     * 商家ID
     */
    private String merchantId;

    /**
    * 店铺名称

    */
    private String shopName;

    /**
    * 店铺类型[10品牌旗舰店 20品牌专卖店 30类目专营店 40运营商自营 50多品类通用型]

    */
    private Integer shopType;

    /**
    * 保证金额度

    */
    private BigDecimal marginQuota;

    /**
    * 保证金余额

    */
    private BigDecimal marginBalance;

    /**
     * 保证金告警线
     */
    private BigDecimal marginDown;

    /**
    * 保证金账户状态(10正常 20预警 30欠费)
    */
    private Integer marginType;

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
