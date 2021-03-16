package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.entity.Merchant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantComplexView;
import com.gs.lshly.biz.support.merchant.mapper.views.MerchantShopView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商家 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Repository
public interface MerchantMapper extends BaseMapper<Merchant> {


    @Select("SELECT distinct m.*,lg.* " +
            "FROM gs_merchant m " +
            "LEFT JOIN gs_shop shop  ON shop.`merchant_id` = m.`id` " +
            "LEFT JOIN gs_legal_dict lg on m.legal_id = lg.id " +
            "where shop.flag=0 and ${ew.sqlSegment}")
    MerchantComplexView getMerchantComplex(@Param(value = "ew") QueryWrapper<MerchantComplexView> ew);

}
