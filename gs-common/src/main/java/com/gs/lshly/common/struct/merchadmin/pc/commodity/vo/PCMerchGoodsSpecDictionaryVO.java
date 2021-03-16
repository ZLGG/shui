package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;

import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryItemVO;
import com.gs.lshly.common.struct.platadmin.commodity.vo.GoodsSpecDictionaryVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Starry
 * @Date 20:26 2020/10/19
 */
public abstract class PCMerchGoodsSpecDictionaryVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsSpecDictionaryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{
        @ApiModelProperty(value = "规格id")
        private String id;

        @ApiModelProperty(value = "规格名称")
        private String name;

        @ApiModelProperty(value = "规格类型")
        private Integer type;

        @ApiModelProperty(value = "规格备注")
        private String remark;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    public static class DetailVO extends ListVO {
        @ApiModelProperty(value = "规格值集合")
        private List<PCMerchGoodsSpecDictionaryItemVO.ListVO> list;
    }

}
