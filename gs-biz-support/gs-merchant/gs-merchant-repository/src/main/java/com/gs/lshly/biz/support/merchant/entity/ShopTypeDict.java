package com.gs.lshly.biz.support.merchant.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
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
 * 店铺类型
 * </p>
 *
 * @author xxfc
 * @since 2020-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_type_dict")
public class ShopTypeDict extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 店铺类型名[品牌旗舰店 品牌专卖店 类目专营店 运营商自营 多品类通用型
     */
    private String typeName;

    /**
     * 店铺类型枚举编号[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]
     */
    private Integer typeCode;


    /**
     * 店铺类型枚举编号[10=品牌旗舰店 20=品牌专卖店 30=类目专营店 40=运营商自营 50=多品类通用型]
     */
    private String typeDesc;


    /**
     * 状态[10=可用 20=禁用]
     */
    private Integer state;

    /**
     * 是否排它[10=否 20=是]
     */
    private Integer isMutex;

    /**
     * 商品数量上限
     */
    private Integer goodsMax;

    /**
     * 商家保证金
     */
    private BigDecimal bail;

    /**
     * 商家保证金告警
     */
    private BigDecimal bailDown;

    /**
     * 店铺名后缀
     */
    private String suffixName;

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
