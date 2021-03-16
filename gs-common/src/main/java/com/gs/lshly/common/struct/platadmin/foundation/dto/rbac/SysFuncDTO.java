package com.gs.lshly.common.struct.platadmin.foundation.dto.rbac;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lxus
 * @since 2020/12/12
 */
public abstract class SysFuncDTO implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel("设置前端路由参数对象")
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "功能id", hidden = true)
        private String id;

        @ApiModelProperty("路由")
        private String frontRouter;

    }

}
