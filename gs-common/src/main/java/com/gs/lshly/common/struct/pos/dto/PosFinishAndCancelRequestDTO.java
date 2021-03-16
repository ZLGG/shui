package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

public abstract class PosFinishAndCancelRequestDTO {
    @Data
    @ApiModel("PosFinishAndCancelRequestDTO.DTO")
    @Accessors(chain = true)
    public static class DTO implements Serializable {

        // 门店id 不允许为空
        private String shop;
        // 平台id，不允许为空
        private String platformId;
        // 退货单号，不允许为空
        private String number;
    }
}
