package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
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
* 商家优惠卷会员令取记录
* </p>
*
* @author zdf
* @since 2021-01-08
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_market_merchant_card_users")
public class MarketMerchantCardUsers extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家优惠卷ID
    */
    private String cardId;

    /**
    * 优惠卷名称
    */
    private String cardName;

    /**
    * 描述
    */
    private String userDescribe;

    /**
    * 适用平台[10=2b 20=2c]
    */
    private String terminal;

    /**
    * 适用会员等级(1,2,3,4,5,6)
    */
    private String onUserLeve;

    /**
    * 满多少
    */
    private BigDecimal toPrice;

    /**
    * 减多少
    */
    private BigDecimal cutPrice;

    /**
    * 有效时间开始
    */
    private LocalDateTime validStartTime;

    /**
    * 有效时间结束
    */
    private LocalDateTime validEndTime;

    /**
    * 使用状态[10=未使用 20=已使用]
    */
    private Integer state;

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
