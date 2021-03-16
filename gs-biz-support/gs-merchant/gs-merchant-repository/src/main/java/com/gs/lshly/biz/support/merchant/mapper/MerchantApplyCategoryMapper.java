package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantApplyCategory;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantApplyCategoryView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商家店铺商品类目申请 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-16
 */
@Repository
public interface MerchantApplyCategoryMapper extends BaseMapper<MerchantApplyCategory> {

    @Select("SELECT ac.*,shop.shop_name,shop.id shop_id,shop.shop_type FROM gs_merchant_apply_category ac " +
            "LEFT JOIN gs_shop shop ON ac.shop_id = shop.id " +
            "WHERE ac.flag = 0 AND shop.flag = 0 AND ${ew.sqlSegment}")
    MerchantApplyCategoryView details(@Param(value = "ew") QueryWrapper<MerchantApplyCategoryView> ew);

    @Select("SELECT ac.*,shop.shop_name,shop.id shop_id,shop.shop_type FROM gs_merchant_apply_category ac " +
            "LEFT JOIN gs_shop shop ON ac.shop_id = shop.id " +
            "WHERE ac.flag = 0 AND shop.flag = 0 AND ${ew.sqlSegment}")
    IPage<MerchantApplyCategoryView> mapperPageList(IPage pager, @Param(value = "ew") QueryWrapper<MerchantApplyCategoryView> ew);

}
