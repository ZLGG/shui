package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
* <p>
* 快递鸟
* </p>
*
* @author zst
* @since 2021-1-29
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_kdniao")
public class StockKdniao extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * ID
    */
    private String id;

    /**
    * 电商id
    */
    @TableField("eBusinessID")
    private String eBusinessID;

    /**
    * 电商加密私钥
    */
    @TableField("appKey")
    private String appKey;

    /**
    * 是否启用订单跟踪查询[10开启 20关闭]
    */
    private Integer isStart;


}
