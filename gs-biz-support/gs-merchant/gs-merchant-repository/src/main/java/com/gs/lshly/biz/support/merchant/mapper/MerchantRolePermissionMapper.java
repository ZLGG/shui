package com.gs.lshly.biz.support.merchant.mapper;

import com.gs.lshly.biz.support.merchant.entity.MerchantRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gs.lshly.biz.support.merchant.mapper.views.RolePermissionView;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 商家角色权限 Mapper 接口
 * </p>
 *
 * @author xxfc
 * @since 2020-10-08
 */
@Repository
public interface MerchantRolePermissionMapper extends BaseMapper<MerchantRolePermission> {

}
