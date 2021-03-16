package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

public abstract  class PosItemsAllListRequestDTO {

    @Data
    @ApiModel("PosItemSkusGetRequestDTO.BaseQTO")
    @Accessors(chain = true)
    public static class DTO extends BaseQTO {

        /**
         * 店铺编号
         */
        private String shopId = "A2688234";

    }

}
