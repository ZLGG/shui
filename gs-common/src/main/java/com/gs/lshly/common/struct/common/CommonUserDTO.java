package com.gs.lshly.common.struct.common;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class CommonUserDTO implements Serializable {

    @Data
    @ApiModel("UserDTO.IdDTO")
    @Accessors(chain = true)
    public static class IdDTO extends BaseDTO {

    }

}
