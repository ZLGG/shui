package com.gs.lshly.common.struct.pos.dto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

public abstract class PosItemSkusGetRequestDTO {

    @Data
    @ApiModel("PosItemSkusGetRequestDTO.BaseQTO")
    @Accessors(chain = true)
    public static class DTO extends BaseQTO {

        /**
         * 店铺编号
         */
        private String shopId = "A2688234";
        /**
         * 商品ID数组，POS入参的时候要转成1,2,3格式
         */
        private List<String> goodsIdList = new ArrayList<>();

    }

}
