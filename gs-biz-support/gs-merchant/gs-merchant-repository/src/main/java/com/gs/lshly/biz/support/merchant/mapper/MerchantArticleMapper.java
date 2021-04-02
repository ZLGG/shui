package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantArticleView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商家文章 Mapper 接口
 * </p>
 *
 * @author hyy
 * @since 2020-11-07
 */
@Repository
public interface MerchantArticleMapper extends BaseMapper<MerchantArticle> {

    @Select("SELECT art.*,ctg.name categoryName,shop.shop_name " +
            "FROM gs_merchant_article art " +
            "LEFT JOIN gs_merchant_article_category ctg ON art.`category_id` = ctg.`id` " +
            "LEFT JOIN gs_shop shop on art.shop_id = shop.id " +
            "where art.flag=0  and ${ew.sqlSegment}")
    IPage<MerchantArticleView> mapperPageList(IPage pager, @Param(value = "ew") QueryWrapper<MerchantArticleView> ew);


    @Select("SELECT art.*, ctg.name category_name,shop.shop_name " +
            "FROM gs_merchant_article art " +
            "LEFT JOIN gs_merchant_article_category ctg ON art.`category_id` = ctg.`id`" +
            "LEFT JOIN gs_shop shop on art.shop_id = shop.id " +
            "WHERE art.id = #{id}")
    MerchantArticleView detail(@Param("id") String id);
}
