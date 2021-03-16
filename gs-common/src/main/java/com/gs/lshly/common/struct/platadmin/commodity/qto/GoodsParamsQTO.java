package com.gs.lshly.common.struct.platadmin.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-09-29
*/
public abstract class GoodsParamsQTO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("类目id")
        private String categoryId;

        @ApiModelProperty("参数组名")
        private String name;

    }
}
