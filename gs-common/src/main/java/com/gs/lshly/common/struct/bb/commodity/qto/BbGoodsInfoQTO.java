package com.gs.lshly.common.struct.bb.commodity.qto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月30日 下午5:02:24
 */
public abstract class BbGoodsInfoQTO implements Serializable {

    @Data
    @ApiModel("GoodsInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        

    }
}