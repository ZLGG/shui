package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Author Starry
 * @Date 14:46 2020/9/27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_goods_category")
public class GoodsCategory extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * 商品类别id
     */
    private String id;

    /**
     * 商品类别名称
     */
    private String gsCategoryName;

    /**
     * 商品类别父id
     */
    private String parentId;

    /**
     * 商品类别级别
     */
    private Integer gsCategoryLevel;

    /**
     *商品类别logo
     */
    private String gsCategoryLogo;

    /**
     * 商品类别服务费率(只有三级有)
     */
    private BigDecimal gsCategoryFee;

    /**
     * 商品类别平台使用费(只有一级有)
     */
    private BigDecimal gsCategoryMoney;

    /**
     * 默认展示模式
     */
    private Integer showType;

    /**
     * 商品类别显示区域
     */
    private Integer useFiled;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 退货设置的天数
     */
    private Integer refundDays;

    /**
     * 换货设置的天数
     */
    private Integer returnDays;

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
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean flag;


}