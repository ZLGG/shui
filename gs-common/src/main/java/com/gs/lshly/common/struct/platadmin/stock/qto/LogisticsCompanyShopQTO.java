package com.gs.lshly.common.struct.platadmin.stock.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 *
 */
public abstract class LogisticsCompanyShopQTO implements Serializable {

    @ApiModel("查询条件")
    @Data
    public static class QueryParam extends BaseQTO {

    }


}
