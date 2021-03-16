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
* 
* </p>
*
* @author Starry
* @since 2020-12-23
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_complaint")
public class TradeComplaint extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 投诉id
    */
    private String id;

    /**
    * 订单ID
    */
    private String tradeId;

    /**
    * 订单商品ID
    */
    private String tradeGoodsId;


    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 订单skuID
     */
    private String skuId;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

    /**
    * 用户账号
    */
    private String userName;

    /**
    * 投诉类型（10=商品问题 20=配送问题 30=支付问题 40=促销活动问题 50=账户问题 60=发票问题 70=系统问题 80=退货/换货问题 90=表扬/投诉工作人员 100=其他）
    */
    private Integer complaintType;

    /**
    * 联系方式
    */
    private String contactWay;

    /**
    * 问题描述
    */
    private String problemDesc;

    /**
    * 投诉时间
    */
    private LocalDateTime complaintTime;

    /**
    * 处理状态
    */
    private Integer handleState;

    /**
    * 用户取消投诉理由
    */
    private String cancenlIdea;

    /**
    * 平台回复
    */
    private String platformReply;

    /**
    * 创建时间
    */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    /**
    * 修改时间
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
