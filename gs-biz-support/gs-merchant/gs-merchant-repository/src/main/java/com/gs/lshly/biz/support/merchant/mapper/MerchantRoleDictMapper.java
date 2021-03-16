package com.gs.lshly.biz.support.merchant.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gs.lshly.biz.support.merchant.entity.MerchantRoleDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.AccountShopView;
import com.gs.lshly.biz.support.merchant.mapper.views.RolePermissionView;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商家帐号角色字典 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Repository
public interface MerchantRoleDictMapper extends BaseMapper<MerchantRoleDict> {

    /**
     * 角色找权限
     * @param qw
     * @return
     */

    @Select("SELECT pm.*,rpm.role_id FROM gs_merchant_role_permission rpm " +
            "LEFT JOIN gs_merchant_permission_dict pm ON rpm.permission_id = pm.id " +
            "WHERE pm.flag =0 AND ${ew.sqlSegment}")
    List<RolePermissionView> selectRolePermission(@Param(value = "ew") QueryWrapper<RolePermissionView> qw);

}
