package com.gs.lshly.common.struct.fy.activity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhaoqiang
 * @Description
 * @date 2020/12/24 下午2:16
 */
public abstract class ActivityQTO implements Serializable {

    @Data
    @ApiModel("ActivityQTO.QTO")
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class QTO extends BaseQTO {

    }
}
