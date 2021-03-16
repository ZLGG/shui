package com.gs.lshly.biz.support.merchant.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 店铺轮播图导航
 * </p>
 *
 * @author 陈奇
 * @since 2020-10-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_shop_banner_navigation")
public class ShopBannerNavigation extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

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
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 逻辑删除标记
     */
    private Boolean flag;


}
