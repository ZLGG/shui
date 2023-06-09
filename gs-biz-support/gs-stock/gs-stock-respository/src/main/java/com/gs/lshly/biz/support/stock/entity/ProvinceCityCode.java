package com.gs.lshly.biz.support.stock.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/4/25 16:26
 */
@Data
@TableName("gs_stock_province_address")
public class ProvinceCityCode extends Model {
    private Long id;

    /**
     * 地址名
     */
    private String name;

    /**
     * 地址代码
     */
    private String code;

    /**
     * 地址别名
     */
    private String alias;

    /**
     * 父类id
     */
    private Long parentId;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 省代码
     */
    private String provinceCode;

    /**
     * 省别名
     */
    private String provinceAlias;

    /**
     * 市
     */
    private String cityName;

    /**
     * 市代码
     */
    private String cityCode;

    /**
     * 市别名
     */
    private String cityAlias;

    /**
     * 地址级别 1:省 2:市 3:区
     */
    private String level;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 删除标识 0: 正常 1: 已删除
     */
    private Boolean deleted;
}
