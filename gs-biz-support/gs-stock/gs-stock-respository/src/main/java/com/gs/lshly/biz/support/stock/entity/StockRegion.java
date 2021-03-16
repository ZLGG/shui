package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;

/**
* <p>
* 行政区划表
* </p>
*
* @author xxfc
* @since 2020-11-18
*/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("gs_stock_region")
public class StockRegion extends Model {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId
    private String id;

    /**
    * 区划编号
    */
    private String code;

    /**
    * 父区划编号
    */
    private String parentCode;

    /**
    * 祖区划编号
    */
    private String ancestors;

    /**
    * 区划名称
    */
    private String name;

    /**
    * 省级区划编号
    */
    private String provinceCode;

    /**
    * 省级名称
    */
    private String provinceName;

    /**
    * 市级区划编号
    */
    private String cityCode;

    /**
    * 市级名称
    */
    private String cityName;

    /**
    * 区级区划编号
    */
    private String districtCode;

    /**
    * 区级名称
    */
    private String districtName;

    /**
    * 镇级区划编号
    */
    private String townCode;

    /**
    * 镇级名称
    */
    private String townName;

    /**
    * 村级区划编号
    */
    private String villageCode;

    /**
    * 村级名称
    */
    private String villageName;

    /**
    * 层级
    */
    private Integer level;

    /**
    * 排序
    */
    private Integer sort;

    /**
    * 备注
    */
    private String remark;


}
