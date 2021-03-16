package com.gs.lshly.common.struct.common;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author lxus
 * @since 2020-12-12
 */
public class PermitNodeDTO implements Serializable {

    @Data
    @ApiModel("CheckedNodeDTO")
    @Accessors(chain = true)
    public static class CheckedNodeDTO extends BaseDTO {

        @ApiModelProperty(value = "用户设置权限id集")
        private List<String> ids;

    }

}
