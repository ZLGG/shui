package com.gs.lshly.biz.support.user.entity;

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
* 常购sku
* </p>
*
* @author xxfc
* @since 2020-12-10
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_frequent_sku")
public class UserFrequentSku extends Model {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
    * 常购商品ID
    */
    private String frequentId;

    /**
    * spu-id
    */
    private String goodsId;

    /**
    * sku-id
    */
    private String skuId;

    /**
    * 数量
    */
    private Integer quantity;

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

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime cdate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime udate;

    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private Boolean flag;


}
