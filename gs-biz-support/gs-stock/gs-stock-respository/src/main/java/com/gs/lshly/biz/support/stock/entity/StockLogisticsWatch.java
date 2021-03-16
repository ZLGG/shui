package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 物流追踪配置
 * </p>
 *
 * @author zzg
 * @since 2020-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_logistics_watch")
public class StockLogisticsWatch extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 参数键名
     */
    private String paramKey;

    /**
     * 参数值
     */
    private String paramValue;

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
    /**
     * 启用状态
     */
    private Integer state;

}
