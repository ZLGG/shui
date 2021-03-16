package com.gs.lshly.common.struct.platadmin.user.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-05
*/
public abstract class UserLeveDictQTO implements Serializable {

    @Data
    @ApiModel("UserLeveDictQTO.QTO")
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("等级适用店铺类型[10=2c 20=2b]")
        private Integer leveType;
    }
}
