package com.gs.lshly.biz.support.foundation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 平台角色功能权限
 * </p>
 *
 * @author lxus
 * @since 2020-12-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gs_sys_role_func")
public class SysRoleFunc implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 功能id
     */
    private String funcId;


}
