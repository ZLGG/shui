package com.gs.lshly.biz.support.commodity.entity;

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
    * @author Starry
    * @since 2020-10-29
    */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("gs_goods_audit_record")
    public class GoodsAuditRecord extends Model {

    private static final long serialVersionUID = 1L;

            /**
            * 主键id
            */
        private String id;

            /**
            * 商品id
            */
        private String goodsId;

            /**
            * 审核状态
            */
        private Integer state;

            /**
            * 审核拒绝原因
            */
        private String refuseRemark;

            /**
            * 操作人
            */
        private String operator;

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
            * 删除标记
            */
            @TableField(fill = FieldFill.INSERT)
            @TableLogic
            private Boolean flag;


}
