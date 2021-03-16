package com.gs.lshly.biz.support.foundation.entity;

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
    * 商家文章栏目
    * </p>
    *
    * @author hyy
    * @since 2020-10-29
    */
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("gs_merchant_article_category")
    public class MerchantArticleCategory extends Model {

    private static final long serialVersionUID = 1L;

            /**
            * id
            */
        private String id;

            /**
            * 栏目名称
            */
        private String name;

            /**
            * 是否底部显示[10=是 20=否]
            */
        private Integer bottom;

            /**
            * 终端[10=2c 20=2b ]
            */
        private Integer terminal;

            /**
            * 排序
            */
        private Integer idx;

            /**
            * 层级[1-2]1级不关联文章
            */
        private Integer leve;

            /**
            * 父ID
            */
        private String parentId;

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
