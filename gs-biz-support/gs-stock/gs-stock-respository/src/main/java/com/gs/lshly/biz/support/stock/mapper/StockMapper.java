package com.gs.lshly.biz.support.stock.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.stock.entity.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.stock.mapper.view.StockAddressView;
import com.gs.lshly.biz.support.stock.mapper.view.StockRoliceView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 库存管理 Mapper 接口
 * </p>
 *
 * @author zhaozhigang
 * @since 2020-10-22
 */
@Repository
public interface StockMapper extends BaseMapper<Stock> {

    @Select("select SUM(quantity) from  gs_stock WHERE goods_id = #{goodId}")
    Integer selectSum(@Param("goodId")String goodId);

    @Select("SELECT SUM(quantity) quantity,goods_id FROM gs_stock " +
            "WHERE flag=0 and ${ew.sqlSegment}")
    List<StockRoliceView> mapperListGoodsNotEmpytStock(@Param(value = "ew") QueryWrapper<StockRoliceView> ew);

    @Select("select sum(quantity) from gs_stock where sku_id = #{skuId}" )
    Integer returnStockNumber(@Param("skuId")String skuId);

    @Update("update  gs_stock  set quantity = #{updateQuantity}  where sku_id = #{skuId}")
    void updateStockNum(@Param("updateQuantity")Integer updateQuantity,@Param("skuId")String skuId);

    @Select("SELECT * FROM (SELECT SUM(quantity) quantity,goods_id " +
            "FROM gs_stock  WHERE shop_id =#{shopId} GROUP BY goods_id) temp " +
            "WHERE quantity < #{quantity}")
    List<StockRoliceView> mapperStockRoliceQuery(@Param("quantity")Integer quantity, @Param("shopId")String ShopId);

}
