package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 店铺导航分类关联商品临时表
 * </p>
 *
 * @author chenyang
 * @since 2021-06-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_goods_shop_navigation_temp")
public class GoodsShopNavigationTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * 店铺导航分类ID
     */
    private String shopNavigation;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 商家ID
     */
    private String merchantId;

    /**
     * 终端（10=2b,20=2c)
     */
    private Integer terminal;

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
