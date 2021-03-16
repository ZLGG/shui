package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableField;
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
* @author zdf
* @since 2021-02-22
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_rights_pos_log")
public class TradeRightsPosLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * POS推送订单退货申请id
    */
    private String id;

    /**
    * 当前时间毫秒数
    */
    private String timestamp;

    /**
    * 6位随机字符
    */
    private String nonce;

    /**
    * POS店编号
    */
    @TableField("posCode")
    private String posCode;

    /**
    * 订单编号
    */
    @TableField("posOrderNo")
    private String posOrderNo;

    /**
    * 订单退货申请单号
    */
    @TableField("posOrderRefundNo")
    private String posOrderRefundNo;

    /**
    * POS店处理意见
    */
    @TableField("posOpinion")
    private String posOpinion;

    /**
    * POS店处理结果(10-同意退货，20-拒绝退货，30-直接退款/收到退货。)
    */
    @TableField("posProcessResult")
    private Integer posProcessResult;

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
