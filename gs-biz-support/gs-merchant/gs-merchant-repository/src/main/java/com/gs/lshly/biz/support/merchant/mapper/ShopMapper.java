package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.Shop;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantShopView;
import com.gs.lshly.biz.support.merchant.mapper.views.ShopSearchView;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 商家店铺 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-13
 */
@Repository
public interface ShopMapper extends BaseMapper<Shop> {

    @Select("select id from gs_shop shop " +
            "left join merchant merch on  shop.merchant_id = merch.id " +
            "where shop.flag = 0 and ${ew.sqlSegment}")
    MerchantShopView getIdByMerchantPhone(@Param(value = "ew") QueryWrapper<Shop> ew);


    @Select("SELECT shop.*,acc.user_name supperAccount,lg.*" +
            "FROM gs_shop shop " +
            "LEFT JOIN gs_merchant m ON shop.`merchant_id` = m.`id` " +
            "LEFT JOIN gs_merchant_account acc ON shop.`main_account_id` = acc.`id` " +
            "LEFT JOIN gs_legal_dict lg on m.legal_id = lg.id " +
            "where shop.flag=0 and ${ew.sqlSegment} ORDER BY shop.cdate desc")
    IPage<MerchantShopView> listShopComplex(IPage pager,@Param(value = "ew") QueryWrapper<MerchantShopView> ew);

    @Select("SELECT shop.*,lg.* " +
            "FROM gs_shop shop " +
            "LEFT JOIN gs_merchant m ON shop.`merchant_id` = m.`id` " +
            "LEFT JOIN gs_legal_dict lg on m.legal_id = lg.id " +
            "where shop.flag=0 and ${ew.sqlSegment}")
    MerchantShopView getShopComplex(@Param(value = "ew") QueryWrapper<MerchantShopView> ew);

    /**
     * ShopFixedMapEnum.启用
     * @param page
     * @param wq
     * @param userLongitude
     * @param userLatitude
     * @return
     */
    @Select("select * from (select s.*," +
            "round(GLength(LineStringFromWKB(LineString(point(s.shop_longitude,s.shop_latitude), point(#{userLongitude},#{userLatitude})))) * 100000, -1) meters, d.shop_ranges" +
            " from gs_shop s" +
            " left join gs_shop_delivery_style d on s.id=d.shop_id" +
            " where s.flag=0 and s.is_fixed_map=20 order by IFNULL(meters,0)) o where ${ew.sqlSegment}")
    IPage<Shop> selectPageContainDistance(IPage<Shop> page,
                                          @Param(value = "ew") QueryWrapper<Shop> wq,
                                          @Param(value = "userLongitude")BigDecimal userLongitude,
                                          @Param(value = "userLatitude")BigDecimal userLatitude);


    /**
     * ShopFixedMapEnum.启用
     * @param wq
     * @param userLongitude
     * @param userLatitude
     * @return
     */
    @Select("select * from (select s.*," +
            "round(GLength(LineStringFromWKB(LineString(point(s.shop_longitude,s.shop_latitude), point(#{userLongitude},#{userLatitude})))) * 100000, -1) meters, d.shop_ranges" +
            " from gs_shop s" +
            " left join gs_shop_delivery_style d on s.id=d.shop_id" +
            " where s.flag=0 and s.is_fixed_map=20 order by IFNULL(meters,0)) o where ${ew.sqlSegment}")
    List<Shop> selectListContainDistance(
                                         @Param(value = "ew") QueryWrapper<Shop> wq,
                                         @Param(value = "userLongitude")BigDecimal userLongitude,
                                         @Param(value = "userLatitude")BigDecimal userLatitude);

    @Select("SELECT shop.*" +
            "FROM gs_shop shop " +
            "LEFT JOIN gs_merchant m ON shop.`merchant_id` = m.`id` " +
            "LEFT JOIN gs_legal_dict lg on m.legal_id = lg.id " +
            "where shop.flag=0 and ${ew.sqlSegment}")
    Shop SearchShopComplex(@Param(value = "ew") QueryWrapper<Shop> ew);



    @Select("SELECT DISTINCT shop.*,feature.sales,feature.`score` " +
            "FROM gs_shop shop " +
            "LEFT JOIN gs_shop_goods_category gory ON shop.`id` = gory.`shop_id` " +
            "LEFT JOIN gs_shop_feature feature ON shop.`id` = feature.`shop_id` " +
            "where shop.flag=0 and ${ew.sqlSegment}")
    IPage<ShopSearchView> bbbPcShopSearch(IPage<ShopSearchView> page, @Param(value = "ew") QueryWrapper<ShopSearchView> ew);


    @Select("SELECT DISTINCT shop.*,feature.sales,feature.`score` " +
            "FROM gs_shop shop " +
            "LEFT JOIN gs_shop_feature feature ON shop.`id` = feature.`shop_id` " +
            "where shop.flag=0 and ${ew.sqlSegment}")
    IPage<ShopSearchView> bbbPcShopKeyWordSearch(IPage<ShopSearchView> page, @Param(value = "ew") QueryWrapper<ShopSearchView> ew);
}
