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
* 商家优惠卷
* </p>
*
* @author zdf
* @since 2020-12-04
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_market_merchant_card")
public class MarketMerchantCard extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 优惠卷前缀
    */
    private String cardPrefix;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家优惠卷名称
    */
    private String cardName;

    /**
    * 描述
    */
    private String cardDescribe;

    /**
     * CardTerminalTypeEnum
    * 适用平台枚举类型[10=pc端 20=wap端 30=app端]
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
    * 优惠卷发行数量
    */
    private Integer quantity;

    /**
    * 每人可领取数量
    */
    private Integer userGetMax;

    /**
    * 已领取总数
    */
    private Integer receivedTotal;

    /**
    * 已使用总数
    */
    private Integer usedTotal;

    /**
    * 可领时间段-开始
    */
    private LocalDateTime getStartTime;

    /**
    * 可领时间段-结束
    */
    private LocalDateTime getEndTime;

    /**
    * 有效时间段-开始
    */
    private LocalDateTime validStartTime;

    /**
    * 有效时间段-结束
    */
    private LocalDateTime validEndTime;

    /**
     * PlatformCardCheckStatusEnum
    * 平台审核状态枚举类型[10=待审 20=通过 30=拒审]
    */
    private Integer state;

    /**
     * 逻辑提交审核标记
     * */

    private Boolean isCommit;
    /**
     * 逻辑取消优惠卷标记
     * */
    private Boolean isCancel;

    /**
    * 拒审原因
    */
    private String revokeWhy;

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
