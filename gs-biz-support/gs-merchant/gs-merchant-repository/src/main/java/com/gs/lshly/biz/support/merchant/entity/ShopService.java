package com.gs.lshly.biz.support.merchant.entity;

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
* 商家在线客服
* </p>
*
* @author xxfc
* @since 2021-01-09
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_service")
public class ShopService extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 账号
    */
    private String account;

    /**
    * 状态/是否启用[10=启用 20=禁用]
    */
    private Integer state;

    /**
    * 热线电话
    */
    private String phone;

    /**
    * 电话启用状态[10=启用 20=禁用]
    */
    private Integer phoneState;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 商家ID
    */
    private String merchantId;

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
