package com.gs.lshly.biz.support.stock.entity;

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
 * 收货地址主表
 * </p>
 *
 * @author xxfc
 * @since 2020-11-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_address")
public class StockAddress extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 归属者ID
     */
    private String ownerId;

    /**
     * 归属者类型[10=店铺 20=会员]
     */
    private Integer ownerType;

    /**
     * 地址标签
     */
    private String lableName;

    /**
     * 省
     */
    private String province;

    /**
     * 省代码
     */
    private String provinceCode;

    /**
     * 市
     */
    private String city;

    /**
     * 市代码
     */
    private String cityCode;

    /**
     * 县/区
     */
    private String county;

    /**
     * 区代号
     */
    private String countyCode;


    /**
     * 邮政编码
     */
    private String postalCode;

    /**
     * 街道
     */
    private String street;

    /**
     * 详细地址
     */
    private String reals;

    /**
     * 完整地址
     */
    private String fullAddress;
    /**
     * 联系人
     */
    private String contactsName;

    /**
     * 手机号
     */
    private String contactsPhone;

    /**
     * 经度
     */
    private BigDecimal longitude;

    /**
     * 纬度
     */
    private BigDecimal latitude;

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
