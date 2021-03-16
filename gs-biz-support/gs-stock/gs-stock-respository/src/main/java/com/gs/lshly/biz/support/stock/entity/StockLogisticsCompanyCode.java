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
    * 
    * </p>
    *
    * @author zzg
    * @since 2020-10-30
    */
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @TableName("gs_stock_logistics_company_code")
    public class StockLogisticsCompanyCode extends Model {

    private static final long serialVersionUID = 1L;

            /**
            * id
            */
        private String id;

            /**
            * 物流公司编码
            */
        private String code;

            /**
            * 物流公司名称
            */
        private String logisticsCompanyAme;

            /**
            * 物流公司网站
            */
        private String website;

            /**
            * 物流公司简称
            */
        private String cohr;


}
