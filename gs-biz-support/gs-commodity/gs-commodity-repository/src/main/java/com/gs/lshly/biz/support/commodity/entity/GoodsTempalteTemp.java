package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品仓储物流配置临时表
 * </p>
 *
 * @author chenyang
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_goods_tempalte_temp")
public class GoodsTempalteTemp implements Serializable {

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
     * 运费id
     */
    private String templateId;

    /**
     * 减库存方式(10-付款减库存，下单减库存)
     */
    private Integer stockSubtractType;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 修改时间
     */
    private Date udate;

    /**
     * 删除标记
     */
    private Boolean flag;


}
