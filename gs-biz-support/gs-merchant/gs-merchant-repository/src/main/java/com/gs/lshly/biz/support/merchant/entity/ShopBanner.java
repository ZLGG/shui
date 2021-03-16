package com.gs.lshly.biz.support.merchant.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 店铺轮播图
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_banner")
public class ShopBanner extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 轮播图导航id
     */
    private String bannerNavigationId;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 文字
     */
    private String text;

    /**
     * 跳转地址
     */
    private String jumpUrl;

    /**
     * 播放速度
     */
    private Float speed;

    /**
     * 排序
     */
    private Integer idx;

    /**
     * 终端[10=2b 20=2c]
     */
    private Integer terminal;

    /**
     * 是否PC显示
     */
    private Integer pcShow;

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
