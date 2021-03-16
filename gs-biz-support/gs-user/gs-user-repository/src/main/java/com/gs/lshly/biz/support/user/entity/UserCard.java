package com.gs.lshly.biz.support.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.time.LocalDateTime;

/**
* <p>
* 我的优惠卷
* </p>
*
* @author xxfc
* @since 2020-10-27
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_user_card")
public class UserCard extends Model {

    private static final long serialVersionUID = 1L;

        /**
        * id
        */
        private String id;

        /**
        * 优惠卷ID
        */
        private String cardId;

        /**
        * 活动卷类型[10=平台购物卷 20=商家优惠卷]
        */
        private Integer cardType;

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
