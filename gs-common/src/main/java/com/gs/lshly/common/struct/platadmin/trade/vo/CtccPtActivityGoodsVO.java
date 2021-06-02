package com.gs.lshly.common.struct.platadmin.trade.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author yangxi
 * @create 2021/5/12 15:58
 */
public class CtccPtActivityGoodsVO implements Serializable {

    @Data
    @ApiModel("CtccPtActivityGoodsVO.goodIDListVO")
    public static class goodIDListVO implements Serializable {
        @ApiModelProperty("商品ID")
        private List<String> goodId;
    }
}
