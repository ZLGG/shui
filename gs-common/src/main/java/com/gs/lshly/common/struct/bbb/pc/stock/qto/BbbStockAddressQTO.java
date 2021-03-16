package com.gs.lshly.common.struct.bbb.pc.stock.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author zzg
* @since 2020-11-02
*/
public abstract class BbbStockAddressQTO implements Serializable {

    @Data
    @ApiModel("BbbStockAddressQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
