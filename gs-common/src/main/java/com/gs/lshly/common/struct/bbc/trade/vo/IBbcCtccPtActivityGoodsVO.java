package com.gs.lshly.common.struct.bbc.trade.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年6月10日 下午3:15:37
 */
@SuppressWarnings("serial")
public class IBbcCtccPtActivityGoodsVO implements Serializable {

    @Data
    @ApiModel("IBbcCtccPtActivityGoodsVO.ListVO")
    public static class ListVO implements Serializable {
        
        /**
         * 商品类别id
         */
        private String id;

        /**
         * 商品类别名称
         */
        private String goodsId;

        /**
         * 商品类别父id
         */
        private String categoryId;

        /**
         * 排序
         */
        private Integer idx;

    }
}
