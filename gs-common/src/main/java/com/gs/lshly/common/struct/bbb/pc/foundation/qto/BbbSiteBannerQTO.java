package com.gs.lshly.common.struct.bbb.pc.foundation.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class BbbSiteBannerQTO implements Serializable {

    @Data
    @ApiModel("BbbSiteBannerQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;

    }
}
