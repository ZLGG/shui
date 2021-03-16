package com.gs.lshly.common.struct.platadmin.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 15:26 2020/9/27
 */
public abstract class GoodsCategoryQTO implements Serializable {

    @Data
    public static class QTO extends BaseQTO {
        @ApiModelProperty(value = "类目id", hidden = true)
        private String id;

        @ApiModelProperty(value = "类目名称")
        private String gsCategoryName;
    }
}
