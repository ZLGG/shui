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
*
* </p>
*
* @author zst
* @since 2020-12-22
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_invoice")
public class TradeInvoice extends Model {

    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private String id;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 订单编号
     */
    private String tradeId;

    /**
     * 会员id
     */
    private String userId;

    /**
     * 会员名字
     */
    private String userName;

    /**
     * 发票类型
     */
    private Integer invoiceRise;

    /**
     * 发票抬头
     */
    private String invoiceName;

    /**
     * 发票号
     */
    private String invoiceNumber;

    /**
     * 税号
     */
    private String taxNumber;

    /**
     * 公司名称
     */
    private String firmName;

    /**
     * 企业地址
     */
    private String registerAddress;

    /**
     * 银行账号
     */
    private String accountNumber;

    /**
     * 企业电话
     */
    private String phone;

    /**
     * 开户银行
     */
    private String openingBank;

    /**
     * 发票类型[10增值税普通发票 20增值税专用发票]
     */
    private Integer invoiceType;

    /**
     * 开票状态[10待开 20已开 30已寄出 40已完成]
     */
    private Integer invoiceStatus;

    /**
     * 物流公司代码
     */
    private String logisticsCode;

    /**
     * 快递单号
     */
    private String expressNumber;


    /**
     * 发票内容
     */
    private String invoiceContent;


    /**
     * 发票地址ID
     */
    private String invoiceAddressId;

    /**
     * 联系人
     */
    private String contactsUser;

    /**
     * 联系人邮箱
     */
    private String contactsEmail;

    /**
     * 金额
     */
    private BigDecimal invoiceAmount;

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
