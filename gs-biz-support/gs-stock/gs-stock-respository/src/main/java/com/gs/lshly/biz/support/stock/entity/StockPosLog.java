package com.gs.lshly.biz.support.stock.entity;

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
* @since 2021-02-24
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_pos_log")
public class StockPosLog extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * POSPOS商品库存推送ID
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
    private String posCode;

    /**
    * 店铺商品sku 69
    */
    @TableField("pos_sku69_Code")
    private String posSku69Code;

    /**
    * 库存变动流水号
    */
    @TableField("stock_change_serial_no")
    private String stockChangeSerialNo;

    /**
    * 库存变化量
    */
    private Integer stockChangeAmount;

    /**
    * 库存总量
    */
    private Integer totalStock;

    /**
    * 是否刷新总库存[0-不刷新；1-刷新平台库存总量]
    */
    private Integer flushTotalStock;

    /**
    * 变更单据图片[JSON]
    */
    private String changeCertificate;

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
