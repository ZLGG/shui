package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountShopView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 商家帐号 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Repository
public interface MerchantAccountMapper extends BaseMapper<MerchantAccount> {


    @Select("select distinct act.*,rd.id role_id,rd.role_name,sp.shop_name,sp.shop_type,act.name,act.type," +
            "act.real_name,CONCAT( act.province,'', act.city ) as merAddress " +
            "from gs_merchant_account act " +
            "left join gs_merchant_account_role aro on act.id = aro.account_id " +
            "left join gs_merchant_role_dict rd on rd.id = aro.role_id " +
            "left join gs_shop sp on act.shop_id = sp.id " +
            "WHERE act.flag = 0 and sp.flag = 0 and ${ew.sqlSegment}")
    IPage<AccountShopView> mapperPage(IPage pager, @Param(value = "ew") QueryWrapper<AccountShopView> we);


    @Select("select distinct act.*,rd.id role_id,rd.role_name,sp.id shop_id,sp.shop_name,sp.shop_type " +
            "from gs_merchant_account act " +
            "left join gs_merchant_account_role aro on act.id = aro.account_id " +
            "left join gs_merchant_role_dict rd on rd.id = aro.role_id " +
            "left join gs_shop sp on act.shop_id = sp.id " +
            "WHERE act.flag = 0 and sp.flag = 0 and ${ew.sqlSegment}")
    AccountShopView mapperOne(@Param(value = "ew") QueryWrapper<AccountShopView> we);

}
