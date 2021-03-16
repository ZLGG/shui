package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author Starry
 * @Date 16:46 2020/10/19
 */
public abstract class PCMerchGoodsCategoryDTO implements Serializable {
    @Data
    @ApiModel("PCMerchGoodsCategoryDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "商品类别id数组")
        private List<String> idList;
    }

    @Data
    @AllArgsConstructor
    @ApiModel("PCMerchGoodsCategoryDTO.IdDTO")
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "商品类别id")
        private String id;
    }

    @Data
    @AllArgsConstructor
    @ApiModel("PCMerchGoodsCategoryDTO.MerchantApplyDTO")
    public static class MerchantApplyDTO extends BaseDTO {



    }
}
