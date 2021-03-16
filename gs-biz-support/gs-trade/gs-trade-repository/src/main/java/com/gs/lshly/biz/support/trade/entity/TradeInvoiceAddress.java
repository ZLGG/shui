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
* @author zst
* @since 2020-12-24
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_trade_invoice_address")
public class TradeInvoiceAddress extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 发票id
    */
    private String invoiceId;

    /**
    * 会员id
    */
    private String userId;

    /**
    * 会员名字
    */
    private String userName;

    /**
    * 省
    */
    private String province;

    /**
     * 省代码
     */
    private String provinceCode;

    /**
     * 市
     */
    private String city;

    /**
     * 市代码
     */
    private String cityCode;

    /**
     * 区
     */
    private String county;

    /**
     * 区代码
     */
    private String countyCode;

    /**
    * 街道
    */
    private String street;

    /**
    * 邮政编码
    */
    private String postalCode;

    /**
    * 收货人姓名
    */
    private String consigneeName;

    /**
    * 收货人电话
    */
    private String consigneePhone;

    /**
    * 是否默认地址
    */
    private Integer isDefault;

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
    * 删除标记
    */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
