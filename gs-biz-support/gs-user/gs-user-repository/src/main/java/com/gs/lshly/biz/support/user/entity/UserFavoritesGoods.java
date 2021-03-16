package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
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
* 收藏商品
* </p>
*
* @author xxfc
* @since 2020-10-28
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_favorites_goods")
public class UserFavoritesGoods extends Model {

    private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 商品ID
    */
    private String goodsId;

    /**
    * 店铺ID
    */
    private String shopId;

    /**
    * 会员ID
    */
    private String userId;

    /**
    * 创建时间
    */
    private LocalDateTime cdate;


}
