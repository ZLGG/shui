package com.gs.lshly.common.struct.platadmin.foundation.qto.rbac;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/12/12
 */
public abstract class SysRoleQTO implements Serializable {

    @Data
    @ApiModel("SysRoleQTO.QTO")
    public static class QTO extends BaseQTO {
        @ApiModelProperty("角色名")
        String name;
    }

}
