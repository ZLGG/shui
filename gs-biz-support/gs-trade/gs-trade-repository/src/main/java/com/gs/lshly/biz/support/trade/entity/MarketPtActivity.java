package com.gs.lshly.biz.support.trade.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 平台活动
* </p>
*
* @author zdf
* @since 2020-11-28
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_market_pt_activity")
public class MarketPtActivity extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

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
    private String activityDescribe;

    /**
     * 店铺类型枚举编号[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]
     * */
    private String typeCode;
    /**
     * 优惠折扣范围
     * */
    private String discountRange;

    /**
     * 提醒方式枚举编号[10=短信 20=邮件]
     * */
    private String reminders;

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
    private LocalDateTime activityStartTime;

    /**
    * 开售结束时间
    */
    private LocalDateTime activityEndTime;

    /**
    * 会员限购数量上限
    */
    private Integer buyMax;

    /**
    * 店铺参加商品数上限
    */
    private Integer goodsMax;

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
