package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gs.lshly.biz.support.merchant.entity.MerchantAccountRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountRoleView;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountShopView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商家帐号角色 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Repository
public interface MerchantAccountRoleMapper extends BaseMapper<MerchantAccountRole> {


    @Select("select dict.role_name,dict.id role_id," +
            "from gs_merchant_role_dict dict " +
            "left gs_merchant_account_role ar on dict.id = ar.role_id " +
            "WHERE dict.flag = 0 and ${ew.sqlSegment}")
    AccountRoleView mapperOne(@Param(value = "ew") QueryWrapper<AccountRoleView> we);

    @Select("select dict.role_name,dict.id role_id," +
            "from gs_merchant_role_dict dict " +
            "left gs_merchant_account_role ar on dict.id = ar.role_id " +
            "WHERE dict.flag = 0 and ${ew.sqlSegment}")
    List<AccountRoleView> mapperList(@Param(value = "ew") QueryWrapper<AccountRoleView> we);
}
