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
@TableName("gs_trade_pos_log")
public class TradePosLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * POS推送订单id
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
    * 订单状态
    */
    @TableField("posOrderState")
    private String posOrderState;

    /**
    * 物流公司
    */
    @TableField("deliveryCompany")
    private String deliveryCompany;

    /**
    * 物流公司编号
    */
    @TableField("deliveryCompanyCode")
    private String deliveryCompanyCode;

    /**
    * 物流编号
    */
    @TableField("deliveryCode")
    private String deliveryCode;

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
