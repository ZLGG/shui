package com.gs.lshly.biz.support.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @Author yangxi
 * @create 2021/4/16 10:53
 */
@Data
@Accessors(chain = true)
@TableName("gs_goods_search_history")
public class GoodsSearchHistory extends Model {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date cdate;

    /**
     * 更新时间
     */
    private Date udate;

    /**
     * 删除标志
     */
    private Boolean flag;

    /**
     * 搜索入口（0-电信商城，1-积分商城）
     */
    private Integer searchEntry;
}
