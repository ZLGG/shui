package com.gs.lshly.biz.support.trade.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
* 
* </p>
*
* @author zdf
* @since 2021-03-22
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_invoice_template")
public class TradeInvoiceTemplate extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 会员id
    */
    private String userId;

    /**
    * 发票类型
    */
    private Integer invoiceRise;

    /**
    * 发票抬头
    */
    private String invoiceName;

    /**
    * 会员名字
    */
    private String userName;

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
    * 是否为默认发票
    */
    private Integer isDefault;



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
