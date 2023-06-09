package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 活动商家参与记录
 * </p>
 *
 * @author yingjun
 * @since 2021-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_market_pt_seckill_merchant")
public class MarketPtSeckillMerchant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 活动ID
     */
    private String seckillId;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 商家ID
     */
    private String merchantId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 标签
     */
    private String label;

    /**
     * 描述
     */
    private String seckillDescribe;

    /**
     * 状态[10=审核 20=未审核 30=审核驳回]
     */
    private String state;

    private String reasonsForRejection;

    /**
     * 报名开始时间
     */
    private LocalDateTime signStartTime;

    /**
     * 报名结束时间
     */
    private LocalDateTime signEndTime;

    /**
     * 活动上线时间
     */
    private LocalDateTime onlineStartTime;

    /**
     * 开售开始时间
     */
    private LocalDateTime seckillStartTime;

    /**
     * 开售结束时间
     */
    private LocalDateTime seckillEndTime;

    /**
     * 会员限购数量上限
     */
    private Integer userBuyMax;

    /**
     * 店铺参加商品数上限
     */
    private Integer shopGoodsMax;

    /**
     * 活动封面图
     */
    private String coverImage;

    /**
     * 开销提醒提前分钟数
     */
    private Integer smsBefore;

    /**
     * 是否短信提醒[10=是 20=否]
     */
    private Integer smsIsTell;

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
