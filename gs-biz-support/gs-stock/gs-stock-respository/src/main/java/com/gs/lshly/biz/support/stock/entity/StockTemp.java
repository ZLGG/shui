package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 库存管理临时表
 * </p>
 *
 * @author chenyang
 * @since 2021-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_stock_temp")
public class StockTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 商家ID
     */
    private String merchantId;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 商品ID
     */
    private String goodsId;

    /**
     * SKU-ID
     */
    private String skuId;

    /**
     * 规格名
     */
    private String specsKey;

    /**
     * 规格值
     */
    private String specsValue;

    /**
     * 库存数量
     */
    private Integer quantity;

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
