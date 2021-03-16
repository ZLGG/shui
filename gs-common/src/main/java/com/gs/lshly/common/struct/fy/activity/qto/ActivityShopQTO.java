package com.gs.lshly.common.struct.fy.activity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/25 上午12:22
 */
public abstract class ActivityShopQTO implements Serializable {

    @Data
    @ApiModel("ActivityShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺名称")
        private String shopName;

    }
}
