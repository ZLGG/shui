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
    * 店铺招牌配置
    * </p>
    *
    * @author Starry
    * @since 2020-10-30
    */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("gs_shop_signboard")
    public class ShopSignboard extends Model {

    private static final long serialVersionUID = 1L;

            /**
            * id
            */
        private String id;

            /**
            * 图片地址
            */
        private String columuPicture;

            /**
            * 是否显示店铺logo[10=是 20=否]
            */
        private Integer isShowLogo;

            /**
            * 是否显示店铺名称[10=是 20=否]
            */
        private Integer isShowName;

            /**
            * 是否显示店铺描述[10=是 20=否]
            */
        private Integer isShowDescription;

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
