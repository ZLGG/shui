package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-06
*/
public abstract class PictureGroupQTO implements Serializable {

    @Data
    @ApiModel("PictureGroupQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }

    @Data
    @ApiModel("查询分组图片")
    @Accessors(chain = true)
    public static class GroupPictureQTO extends BaseQTO {

        @ApiModelProperty("分组id")
        private String groupId;

    }
}
