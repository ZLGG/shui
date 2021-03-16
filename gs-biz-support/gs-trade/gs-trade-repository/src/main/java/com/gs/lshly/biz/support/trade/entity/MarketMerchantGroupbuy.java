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
* 商家团购促销
* </p>
*
* @author zdf
* @since 2020-12-10
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_market_merchant_groupbuy")
public class MarketMerchantGroupbuy extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 团购活动名称
    */
    private String groupbuyName;

    /**
    * 描述
    */
    private String groupbuyDescribe;

    /**
    * 适用平台[10=pc端 20=wap端 30=app端]（CardTerminalTypeEnum）
    */
    private String terminal;

    /**
    * 适用会员等级(1,2,3,4,5,6)
    */
    private String onUserLeve;

    /**
     * 会员可参与次数
     */
    private Integer userDoNumber;
    /**
    * 活动有效时间段-开始
    */
    private LocalDateTime validStartTime;

    /**
    * 活动有效时间段-结束
    */
    private LocalDateTime validEndTime;

    /**
    * 平台审核状态[10=待审 20=通过 30=拒审]
    */
    private Integer state;

    /**
    * 逻辑提交审核标记
    */
    private Boolean isCommit;

    /**
    * 逻辑取消标记
    */
    private Boolean isCancel;

    /**
    * 拒审原因
    */
    private String  revokeWhy;

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
