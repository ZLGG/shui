package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
* 收地址管理子表
* </p>
*
* @author xxfc
* @since 2020-11-08
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_address_child")
public class StockAddressChild extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
    * 地址主表ID
    */
    private String addressId;

    /**
    * 地址类型[10=收货 20=发票 30=退货仓库]
    */
    private Integer addressType;

    /**
     * 是否默认[0=否 1=是]
     */
    private Integer isDefault;

    /**
    * 是否启用[0=否 1=是]
    */
    private Integer state;



}
