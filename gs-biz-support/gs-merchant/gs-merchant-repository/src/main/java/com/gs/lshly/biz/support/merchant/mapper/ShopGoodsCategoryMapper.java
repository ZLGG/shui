package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.ShopGoodsCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.ShopGoodsCategoryView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 店铺商品类目 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
@Repository
public interface ShopGoodsCategoryMapper extends BaseMapper<ShopGoodsCategory> {

    @Select("SELECT c.id,c.category_id,s.id shop_id,s.shop_name FROM gs_shop_goods_category c " +
            "LEFT JOIN gs_shop s ON c.shop_id = s.id " +
            "WHERE c.flag = 0 AND s.flag = 0 AND ${we.sqlSegment}")
    IPage<ShopGoodsCategoryView> pagelist(IPage pager, @Param(value = "we") QueryWrapper<ShopGoodsCategory> qw);

}
