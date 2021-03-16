package com.gs.lshly.biz.support.stock.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.stock.entity.StockAddressChild;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 * 收地址管理子表 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-11-08
 */
@Repository
public interface StockAddressChildMapper extends BaseMapper<StockAddressChild> {

    @Select("SELECT count(1) " +
            "FROM gs_stock_address addr " +
            "LEFT JOIN gs_stock_address_child child ON addr.`id` = child.address_id " +
            "WHERE addr.flag=0 and ${ew.sqlSegment}")
    int countDefault(@Param(value = "ew") QueryWrapper<StockAddressChild> ew);

}
