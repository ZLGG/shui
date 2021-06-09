package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chenyang
 * @since 2021-06-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_goods_spec_info_temp")
public class GoodsSpecInfoTemp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private String id;

    /**
     * 商品id
     */
    private String goodId;

    /**
     * 规格名称
     */
    private String specName;

    /**
     * 规格值名称
     */
    private String specValue;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 删除标记
     */
    private Boolean flag;


}
