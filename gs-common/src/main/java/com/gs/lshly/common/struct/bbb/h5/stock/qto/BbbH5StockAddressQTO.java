package com.gs.lshly.common.struct.bbb.h5.stock.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zzg
* @since 2020-11-02
*/
public abstract class BbbH5StockAddressQTO implements Serializable {

    @Data
    @ApiModel("BbbH5StockAddressQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("地址类型[10=收货 20=发票 30=发货 40=退货]")
        private Integer addressType;
    }
}
