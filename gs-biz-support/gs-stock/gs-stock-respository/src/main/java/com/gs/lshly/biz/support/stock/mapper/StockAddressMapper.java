package com.gs.lshly.biz.support.stock.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.stock.entity.StockAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockAddressView;
import com.gs.lshly.biz.support.stock.mapper.view.StockDefaultAddressView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 收货地址主表 Mapper 接口
 * </p>
 *
 * @author zzg
 * @since 2020-11-02
 */
@Repository
public interface StockAddressMapper extends BaseMapper<StockAddress> {


    @Select("SELECT ad.*,child.state,child.is_default " +
            "FROM gs_stock_address ad " +
            "LEFT JOIN gs_stock_address_child child  ON ad.`id` = child.`address_id` " +
            "where ad.flag=0 and ${ew.sqlSegment}")
    List<StockAddressView> mapperList(@Param(value = "ew") QueryWrapper<StockAddressView> ew);

    @Select("SELECT ad.*,child.state,child.is_default " +
            "FROM gs_stock_address ad " +
            "LEFT JOIN gs_stock_address_child child  ON ad.`id` = child.`address_id` " +
            "where ad.flag=0 and ${ew.sqlSegment}")
    StockAddressView mapperOne(@Param(value = "ew") QueryWrapper<StockAddressView> ew);

    /**
     * 查询该用户是否有默认收获地址
     * @param ew
     * @return
     */
    @Select("SELECT distinct sac.* FROM gs_stock_address_child sac\n" +
            "LEFT JOIN gs_stock_address sa ON sac.address_id = sa.id \n" +
            "WHERE sa.flag = 0 and ${ew.sqlSegment}")
    StockDefaultAddressView getStockDefaultAddressView(@Param(value = "ew") QueryWrapper<StockDefaultAddressView> ew);

    /**
     * 查询个人收货分页列表
     * @param ew
     * @param page
     * @return
     */
    @Select("SELECT DISTINCT gsa.*,gsac.is_default FROM gs_stock_address gsa\n" +
            "LEFT JOIN gs_stock_address_child gsac ON gsa.id = gsac.address_id\n" +
            "WHERE  gsa.flag = 0 and ${ew.sqlSegment}")
    IPage<StockAddressView> pageStockAddressView(@Param(value = "ew") QueryWrapper<StockAddressView> ew,IPage<StockAddressView> page);
}
