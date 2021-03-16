package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
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
* 活动商家参与记录
* </p>
*
* @author zdf
* @since 2020-12-01
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_market_pt_activity_merchant")
public class MarketPtActivityMerchant extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 活动ID
    */
    private String activityId;

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
    private String activityDescribe;

    /**ActivitySignEnum
     * 状态[10=已审核 20=待审核 30=审核驳回]
     *
     * */
    private String  state;
    /**
     * 审核失败驳回原因
     * */
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
    private LocalDateTime activityStartTime;

    /**
    * 开售结束时间
    */
    private LocalDateTime activityEndTime;

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
