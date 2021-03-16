package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
* <p>
* 售后表
* </p>
*
* @author oy
* @since 2020-12-06
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_rights")
public class TradeRights extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * 售后ID
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
    * 商家ID
    */
    private String merchantId;

    /**
    * 订单编号
    */
    private String orderCode;

    /**
    * 退款金额
    */
    private BigDecimal refundAmount;

    /**
     * 退款备注
     * */
    private String refundRemarks;

    /**
     * TradeRightsStateEnum
    * 状态(10:申请,20:驳回,30:通过,40:已退货,50:收到退货,60:等待退款,70:退款完成,80:等待发货,90:已发货,91:确认收货,95:用户取消,99:完成)
    */
    private Integer state;

    /**
    * 售后类型(10:换货,20:仅退款,30:退货退款)
    */
    private Integer rightsType;

    /**
    * 申请售后原因
    */
    private Integer rightsReasonType;

    /**
    * 申请售后说明
    */
    private String rightsRemark;

    /**
    * 退货方式(10:自行寄回,20:上门取件)
    */
    private Integer returnType;
    /**
     * 退货物流公司名字
     */
    private String returnGoodsLogisticsName;
    /**
     * 退货物流单号
     */
    private String returnGoodsLogisticsNum;

    /**
     * 退货寄出时间
     */
    private LocalDateTime returnGoodsLogisticsDate;
    /**
     * 换货商机寄回物流公司名字
     */
    private String sendBackLogisticsName;
    /**
     * 换货商机寄回物流单号
     */
    private String sendBackLogisticsNum;

    /**
     * 换货商机寄出时间
     */
    private LocalDateTime sendBackLogisticsDate;
    /**
    * 审核拒绝原因
    */
    private String rejectReason;

    /**
    * 申请时间
    */
    private LocalDateTime applyTime;

    /**
    * 完成时间
    */
    private LocalDateTime completeTime;

    /**
    * 审批人名字
    */
    private String handPersonName;

    /**
    * 审批人ID
    */
    private String merchantAccountId;

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
